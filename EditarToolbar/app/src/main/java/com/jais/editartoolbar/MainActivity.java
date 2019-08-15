package com.jais.editartoolbar;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseReference reff;
    DatosPersonas datosPersonas;

    public EditText edtNombre;
    public EditText edtDNI;
    public EditText edtMail;
    public EditText edtAdress;
    public Button btnAceptar;
    public ListView lv;

    private ArrayList<DatosPersonas> lvdatos = new ArrayList<DatosPersonas>();

    ArrayAdapter<DatosPersonas> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAdress= findViewById(R.id.edtAddress);
        edtDNI = findViewById(R.id.edtDNI);
        edtMail = findViewById(R.id.edtMail);
        edtNombre = findViewById(R.id.edtNombre);
        lv = findViewById(R.id.lv);
        lv.setVisibility(View.INVISIBLE);
        btnAceptar = findViewById(R.id.btnAceptar);
        datosPersonas = new DatosPersonas();


        reff = FirebaseDatabase.getInstance().getReference().child("DatosPersonas");

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datosPersonas.setdni(edtDNI.getText().toString().trim());
                datosPersonas.setaddress(edtAdress.getText().toString().trim());
                datosPersonas.setmail(edtMail.getText().toString().trim());
                datosPersonas.setnombre(edtNombre.getText().toString().trim());

                reff.child(edtNombre.getText().toString()).setValue(datosPersonas);




                Toast.makeText(getApplicationContext(), "Informacion enviada correctamente", Toast.LENGTH_SHORT).show();

                edtNombre.setVisibility(View.INVISIBLE);
                edtAdress.setVisibility(View.INVISIBLE);
                edtDNI.setVisibility(View.INVISIBLE);
                edtMail.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);

                lv.setAdapter(adaptador);
                adaptador.add(datosPersonas);
               // reff.setValue(datosPersonas);

            }
        });

        adaptador= new ArrayAdapter<DatosPersonas >(this, android.R.layout.simple_expandable_list_item_1, lvdatos);
        lvdatos.add(new DatosPersonas("Nombre", "DNI", "Mail", "Direccion"));



    }
        
}





