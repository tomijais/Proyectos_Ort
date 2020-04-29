package com.examples.intentexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
{
    public TextView txtInvitado;
    public String textoRecibido;

    private String sR1;
    private Double dR1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtInvitado = findViewById(R.id.txt_invitado);

        textoRecibido = getIntent().getStringExtra("TEXTOINGRESADO");
        txtInvitado.setText(textoRecibido);


        dR1 = Double.valueOf(sR1); // convertir de String a Double
        sR1 = String.valueOf(dR1);// convertir de Double a String


    }
}
