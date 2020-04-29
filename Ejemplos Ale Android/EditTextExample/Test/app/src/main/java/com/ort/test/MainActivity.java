package com.ort.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private TextView txtCartel;
    private Button btnShow;
    private EditText edtIngreso;

    private String textoIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sincronizo mi recurso grafico con mi objeto
        txtCartel = findViewById(R.id.txt_cartel);
        btnShow = findViewById(R.id.btn_show);
        edtIngreso = findViewById(R.id.edt_ingreso);

        // Asigno el string entre parentesis en mi TextView
        txtCartel.setText("Ingrese un texto:");

        btnShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(edtIngreso.length() > 0) {
                    textoIngresado = edtIngreso.getText().toString();

                    if(textoIngresado.equals("hola"))
                    {
                        Toast.makeText(getApplicationContext(), "Texto correcto", Toast.LENGTH_SHORT).show();
                    }

                    txtCartel.setText(textoIngresado);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Texto vacio", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
