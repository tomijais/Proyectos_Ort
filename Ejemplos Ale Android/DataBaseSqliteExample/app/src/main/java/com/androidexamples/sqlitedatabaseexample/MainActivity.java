package com.androidexamples.sqlitedatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
* Para debug el disopsitivo debe estar conectado a la misma red que la PC
*
* */

import com.androidexamples.sqlitedatabaseexample.Database.UsuariosSqliteHelper;

public class MainActivity extends AppCompatActivity
{


    private Button btnAdd;
    private Button btnDelete;
    private Button btnShow;

    private TextView txtInfo;

    private SQLiteDatabase db;
    private UsuariosSqliteHelper usdbh;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnShow = findViewById(R.id.btn_show);

        txtInfo = findViewById(R.id.txt_info);


        usdbh = new UsuariosSqliteHelper(this, "MyDatabase.db", null, 1);
        db = usdbh.getWritableDatabase();



        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Creamos el registro a insertar como objeto ContentValues
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("NOMBRE", "Ale");
                nuevoRegistro.put("DNI",10);

//Insertamos el registro en la base de datos
                db.insert("USUARIOS", null, nuevoRegistro);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                db.delete("USUARIOS", "DNI=10", null);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String[] campos = new String[] {"NOMBRE", "DNI"};
               // String[] args = new String[] {"10","Ale"};

              //  Cursor c = db.query("USUARIOS", campos, "DNI=? AND NOMBRE=?", args, null,       null, null);
                Cursor c = db.query("USUARIOS", campos, null, null, null,       null, null);

                //Nos aseguramos de que existe al menos un registro
                if (c.moveToFirst())
                {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String nombre= c.getString(0);
                        int dni = c.getInt(1);

                        txtInfo.setText(nombre+String.valueOf(dni));
                    } while(c.moveToNext());
                }
                else
                {
                    txtInfo.setText("");

                }
            }
        });


            }
}
