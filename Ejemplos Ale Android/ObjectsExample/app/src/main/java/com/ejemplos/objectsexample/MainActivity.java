package com.ejemplos.objectsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    public float pepe;
    public Mesa mesa1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesa1 = new Mesa(1.32,"verde",6);
        mesa1.setColor("violeta");

    }
}
