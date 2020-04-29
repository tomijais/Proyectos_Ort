package com.manadigital.bluetoothtx;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity
{
    public BluetoothSPP bt;

    public TextView txtmonitor;

    public EditText edtinput;

    public Button btnenviar;
    public ImageView btnconectar;

   public  LineGraphSeries<DataPoint> series;

   public GraphView graph;

   public  Double x= 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtmonitor = (TextView) findViewById(R.id.txtmonitor);


        btnconectar = (ImageView) findViewById(R.id.btnconectar);

        graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(x,0)

        });
        graph.addSeries(series);



        bt = new BluetoothSPP(this);

        if (!bt.isBluetoothEnabled())
        {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else
        {
            if (!bt.isServiceAvailable())
            {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                // setup();
            }
        }

        btnconectar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }

            }

        });

//        btnenviar.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                if(edtinput.length()!=0)
//                bt.send(edtinput.getText().toString(), true);
//
//            }
//
//        });




        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener()
        {
            public void onDataReceived(byte[] data, String message)
            {

                series.appendData(new DataPoint(x,Double.valueOf(message)),true,10);
                graph.addSeries(series);
                x=x+1;

                txtmonitor.setText(message);

               Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE)
        {
            if(resultCode == Activity.RESULT_OK)
                bt.connect(data);
        }
        else if(requestCode == BluetoothState.REQUEST_ENABLE_BT)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                //setup();
            }
            else
            {
                // Do something if user doesn't choose any device (Pressed back)
            }
        }
    }

}
