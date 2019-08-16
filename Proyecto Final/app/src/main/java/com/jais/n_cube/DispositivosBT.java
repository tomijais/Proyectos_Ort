package com.jais.n_cube;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.ImageView;


public class


DispositivosBT extends AppCompatActivity {


    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";


    private ImageChangeBroadcastReceiver imageChangeBroadcastReceiver;
    private AlertDialog enableNotificationListenerAlertDialog;

    //1)
    // Depuracion LOGCAT                CAMBIAR STRING POR LA CLASE QUE USO
    private static final String TAG = "DispositivosBT"; // <<<< PARTE A MODIFICAR
    //Declaracion de ListView
    ListView IdLista;
    //String que se enviara a la actividad principal, mainactivity
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // Declaracion de campos
    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_bt);



        // If the user did not turn the notification listener service on we prompt him to do so
        if(!isNotificationServiceEnabled()){
            enableNotificationListenerAlertDialog = buildNotificationServiceAlertDialog();
            enableNotificationListenerAlertDialog.show();
        }

        // Finally we register a receiver to tell the MainActivity when a notification has been received
        imageChangeBroadcastReceiver = new ImageChangeBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jais.n_cube.NotificationListenerExampleService");
        registerReceiver(imageChangeBroadcastReceiver,intentFilter);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        //-----------------------
        VerificarEstadoBT();

        //Inicializa la array que contendra la lista de dispositivos BT visculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.nombre_dispositivos);////<<<<PARTE A MODIFICAR
        //Presenta los dispositivis visculados en el List View
        IdLista = findViewById(R.id.IdLista);
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        IdLista.setOnItemClickListener(mDeviceClickListener);
        //Obtiene el adaptador locar BT adapter;
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        //------------------- EN CASO DE ERROR -------------------------------------
        //SI OBTIENES UN ERROR EN LA LINEA (BluetoothDevice device : pairedDevices)
        //CAMBIA LA SIGUIENTE LINEA POR
        //Set <BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        //------------------------------------------------------------------------------

        //Obtiene un conjunto de dispositivos emparejados y agrega a "pairedDevices"
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Adiciona un dispositivos previo emparejado al array
        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device : pairedDevices) { //EN CASO DE ERROR LEER LA ANTERIOR EXPLICACION
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(imageChangeBroadcastReceiver);
    }

    /**
     * Change Intercepted Notification Image
     * Changes the MainActivity image based on which notification was intercepted
     * @param notificationCode The intercepted notification code
     */
    private void changeInterceptedNotificationImage(int notificationCode){
        switch(notificationCode){
            case NotificationListenerExampleService.InterceptedNotificationCode.FACEBOOK_CODE:
                Toast.makeText(this, "FACEBOOK", Toast.LENGTH_SHORT).show();
                Intent Facebook = new Intent(getApplicationContext(), UserInterfaz.class);
                Facebook.putExtra("FACEBOOK","FACEBOOK");

                break;
            case NotificationListenerExampleService.InterceptedNotificationCode.INSTAGRAM_CODE:
                Toast.makeText(this, "INSTAGRAM", Toast.LENGTH_SHORT).show();
                Intent Instagram = new Intent(getApplicationContext(), UserInterfaz.class);
                Instagram.putExtra("INSTAGRAM","INSTAGRAM");

                break;
            case NotificationListenerExampleService.InterceptedNotificationCode.WHATSAPP_CODE:
                Toast.makeText(this, "WHATSAPP", Toast.LENGTH_SHORT).show();
               // Intent Whatsapp = new Intent(getApplicationContext(), UserInterfaz.class);
               // Whatsapp.putExtra("WHATSAPP","WHATSAPP");


                Bundle NotificacionWhatsappEnviada;
                NotificacionWhatsappEnviada = new Bundle();
                NotificacionWhatsappEnviada.putString("Whatsapp","Whatsapp");
                Intent UserInterfaz;
                UserInterfaz = new Intent(DispositivosBT.this, UserInterfaz.class);
                UserInterfaz.putExtras(NotificacionWhatsappEnviada);
                Log.d("jais","01");

                break;
            case NotificationListenerExampleService.InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE:
                Toast.makeText(this, "OTRAS", Toast.LENGTH_SHORT).show();
                Intent Otras = new Intent(getApplicationContext(), UserInterfaz.class);
                Otras.putExtra("OTRAS","OTRAS");

                break;
        }
    }


    /**
     * Is Notification Service Enabled.
     * Verifies if the notification listener service is enabled.
     * Got it from: https://github.com/kpbird/NotificationListenerService-Example/blob/master/NLSExample/src/main/java/com/kpbird/nlsexample/NLService.java
     * @return True if enabled, false otherwise.
     */
    private boolean isNotificationServiceEnabled(){
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Image Change Broadcast Receiver.
     * We use this Broadcast Receiver to notify the Main Activity when
     * a new notification has arrived, so it can properly change the
     * notification image
     * */
    public class ImageChangeBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int receivedNotificationCode = intent.getIntExtra("Notification Code",-1);
            changeInterceptedNotificationImage(receivedNotificationCode);
        }
    }

    /**
     * Build Notification Listener Alert Dialog.
     * Builds the alert dialog that pops up if the user has not turned
     * the Notification Listener Service on yet.
     * @return An alert dialog which leads to the notification enabling screen
     */
    private AlertDialog buildNotificationServiceAlertDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.notification_listener_service);
        alertDialogBuilder.setMessage(R.string.notification_listener_service_explanation);
        alertDialogBuilder.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If you choose to not enable the notification listener
                        // the app. will not work as expected
                    }
                });
        return(alertDialogBuilder.create());
    }




    // Configura un (on-click) para la lista
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {

            // Obtener la dirección MAC del dispositivo, que son los últimos 17 caracteres en la vista
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Realiza un intent para iniciar la siguiente actividad
            // mientras toma un EXTRA_DEVICE_ADDRESS que es la dirección MAC.
            Intent i = new Intent(DispositivosBT.this, UserInterfaz.class);//<-<- PARTE A MODIFICAR >->->
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
        }
    };

    private void VerificarEstadoBT() {
        // Comprueba que el dispositivo tiene Bluetooth y que está encendido.
        mBtAdapter= BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth Activado...");
            } else {
                //Solicita al usuario que active Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);

            }
        }
    }


}








