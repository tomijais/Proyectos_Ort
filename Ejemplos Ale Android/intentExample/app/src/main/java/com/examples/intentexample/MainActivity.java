package com.examples.intentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public EditText edtName;
    public Button btnOk;

    public String textoIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edt_name);
        btnOk = findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textoIngresado = edtName.getText().toString();
                edtName.setText("");
              //  Toast.makeText(getApplicationContext(),"bienvenido " +textoIngresado, Toast.LENGTH_SHORT).show();
                Intent intentMain2 = new Intent(getApplicationContext(),Main2Activity.class);

                intentMain2.putExtra("TEXTOINGRESADO",textoIngresado);
                intentMain2.putExtra("NUMEROINGREADO",10);

                startActivity(intentMain2);

//                finish();

            }
        });

    }
}
