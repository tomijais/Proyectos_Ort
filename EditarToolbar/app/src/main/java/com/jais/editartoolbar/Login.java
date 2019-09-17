package com.jais.editartoolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button btnLogin ;
    private EditText edtLogNombre, edtLogContraseña;
    private final String Usuario = "";
    private final String Contraseña = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogNombre = findViewById(R.id.edtLogNombre);
        edtLogContraseña = findViewById(R.id.edtLogContraseña);

        edtLogNombre.setText("");
        edtLogContraseña.setText("");

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtLogNombre.getText().toString();
                String psw = edtLogContraseña.getText().toString();

                if (user.equals(Usuario) && psw.equals(Contraseña)) {
                    Intent i = new Intent (getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    edtLogNombre.setText("");
                    edtLogContraseña.setText("");
                    Toast.makeText(Login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}