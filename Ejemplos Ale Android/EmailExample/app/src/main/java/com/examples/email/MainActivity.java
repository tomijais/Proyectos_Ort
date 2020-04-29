package com.examples.email;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private EditText edtTexto;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtTexto = findViewById(R.id.edt_texto);
        btnEnviar = findViewById(R.id.btn_enviar);


        btnEnviar.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (edtTexto.length()!=0)
                {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    //   i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"utndispositivosmoviles@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Consulta");
                    i.putExtra(Intent.EXTRA_TEXT ,edtTexto.getText().toString());
                    i.setType("message/rfc822");
                    i.setPackage("com.google.android.gm");

                    try
                    {
                        //  startActivity(Intent.createChooser(i, "Send mail..."));
                        startActivity(i);
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {
                        Toast.makeText(MainActivity.this, "No se puede enviar la consulta", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Falta rellenar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

