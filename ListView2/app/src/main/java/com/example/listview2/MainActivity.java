package com.example.listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Data data;

    EditText edtName;
    EditText edtMail;
    EditText edtDNI;
    EditText edtAddres;
    Button btnAceptar;
    ListView lv;

    ArrayList<Data> ArrayData = new ArrayList();

    ArrayAdapter<Data> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtMail = findViewById(R.id.edtMail);
        edtDNI = findViewById(R.id.edtDNI);
        edtAddres= findViewById(R.id.edtAddres);
        btnAceptar = findViewById(R.id.btnAceptar);
        lv = findViewById(R.id.lv);
        lv.setVisibility(View.INVISIBLE);

        data = new Data();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data.setName(edtName.getText().toString().trim());
                data.setMail(edtMail.getText().toString().trim());
                data.setDNI(edtDNI.getText().toString().trim());
                data.setAddres(edtAddres.getText().toString().trim());

                Toast.makeText(MainActivity.this, "Guardado con exito", Toast.LENGTH_SHORT).show();

                lv.setAdapter(adapter);
                adapter.add(data);

                edtName.setVisibility(View.INVISIBLE);
                edtMail.setVisibility(View.INVISIBLE);
                edtDNI.setVisibility(View.INVISIBLE);
                edtAddres.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);

            }
        });


        ArrayData.add(new Data("Nombre", "Mail", "DNI", "Direccion"));
        adapter = new ArrayAdapter<Data>(this,android.R.layout.simple_expandable_list_item_1);

    }
}
