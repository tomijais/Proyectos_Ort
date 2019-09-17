package com.jais.editartoolbar;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseReference reff;
    User user;

    public EditText edtNombre;
    public EditText edtDNI;
    public EditText edtMail;
    public EditText edtAdress;
    public Button btnAceptar, btnAgregar;
    public FloatingActionButton ActionButton;
    public ListView lv;

    private int p;

    private Toolbar toolbar;

    public ArrayList<User> lvdatos = new ArrayList<User>();

    ArrayAdapter<User> adaptador;

    private boolean eliminando = false, editando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-------------------------------------------------------------------------------------------------------------------------- Casteo e invisivilizo lo q hay que invisililizar
        edtAdress = findViewById(R.id.edtAdress);
        edtAdress.setVisibility(View.INVISIBLE);

        edtDNI = findViewById(R.id.edtDNI);
        edtDNI.setVisibility(View.INVISIBLE);

        edtMail = findViewById(R.id.edtMail);
        edtMail.setVisibility(View.INVISIBLE);

        edtNombre = findViewById(R.id.edtNombre);
        edtNombre.setVisibility(View.INVISIBLE);

        btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setVisibility(View.INVISIBLE);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setVisibility(View.INVISIBLE);

        ActionButton = findViewById(R.id.abAgregar);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        user = new User();
        lv = findViewById(R.id.lv);

        reff = FirebaseDatabase.getInstance().getReference().child("User");
        adaptador = new ArrayAdapter<User>(this, android.R.layout.simple_expandable_list_item_1, lvdatos);

        //-------------------------------------------------------------------------------------------------------------------------- Primer valor en el ListView

       /*
               User Tomas = new User("Tomas Jais", "43819469", "tomijais@gmail.com", "yerbal 41");
               adaptador.add(Tomas);
               lv.setAdapter(adaptador);
        */

        //-------------------------------------------------------------------------------------------------------------------------- Action Button AGREGAR

        ActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNombre.setVisibility(View.VISIBLE);
                edtMail.setVisibility(View.VISIBLE);
                edtAdress.setVisibility(View.VISIBLE);
                edtDNI.setVisibility(View.VISIBLE);
                btnAgregar.setVisibility(View.VISIBLE);


                edtNombre.setText("");
                edtMail.setText("");
                edtAdress.setText("");
                edtDNI.setText("");
                ActionButton.hide();

                getSupportActionBar().hide();
                lv.setVisibility(View.INVISIBLE);
            }
        });
        //-------------------------------------------------------------------------------------------------------------------------- Boton Aceptar

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().show();
                //-------------------------------------------------------------------------------------------- Tomo los datos y los guardo
                lvdatos.get(p).setnombre(edtNombre.getText().toString());
                lvdatos.get(p).setmail(edtMail.getText().toString());
                lvdatos.get(p).setaddress(edtAdress.getText().toString());
                lvdatos.get(p).setdni(edtDNI.getText().toString());
                //----------------------------------------------------------------- Guardo este en especial para tener los nodos a partir del DNI
                final String rama = edtDNI.getText().toString();
                //------------------------------------------------------------------------------------ Envio los datos a FireBase
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        reff.child(rama).child("Name").setValue(edtNombre.getText().toString());
                        reff.child(rama).child("Mail").setValue(edtMail.getText().toString());
                        reff.child(rama).child("Address").setValue(edtAdress.getText().toString());
                        reff.child(rama).child("DNI").setValue(edtDNI.getText().toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //------------------------------------------------------------------------------------ Subo los datos al ListView

                lv.setAdapter(adaptador);

                Toast.makeText(getApplicationContext(), "Informacion guardada correctamente", Toast.LENGTH_SHORT).show();

                edtNombre.setVisibility(View.INVISIBLE);
                edtAdress.setVisibility(View.INVISIBLE);
                edtDNI.setVisibility(View.INVISIBLE);
                edtMail.setVisibility(View.INVISIBLE);
                lv.setVisibility(View.VISIBLE);
                ActionButton.show();
                btnAceptar.setVisibility(View.INVISIBLE);
            }
        });
        //-------------------------------------------------------------------------------------------------------------------------- Boton Agregar

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportActionBar().show();
                edtDNI.setVisibility(View.VISIBLE);
                edtNombre.setVisibility(View.VISIBLE);
                edtAdress.setVisibility(View.VISIBLE);
                edtMail.setVisibility(View.VISIBLE);
                lv.setVisibility(View.INVISIBLE);

                //--------------------------------------------------------------------------------------------- Guardo cada dato en una variable nueva
                final String nombre = String.valueOf(edtNombre.getText());
                final String mail = String.valueOf(edtMail.getText());
                final String direccion = String.valueOf(edtAdress.getText());
                final String dni = String.valueOf(edtDNI.getText());

                if (!nombre.equals("") && !mail.equals("") && !direccion.equals("") && !dni.equals("")) {  //----------- Si en todas las casillas hay algo escrito...
                    final User x = new User(nombre, mail, direccion, dni);

                    btnAgregar.setVisibility(View.INVISIBLE);
                    ActionButton.show();

                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            reff.child(dni).setValue(x);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    lvdatos.add(x);
                    lv.setAdapter(adaptador);

                    edtNombre.setText("");
                    edtMail.setText("");
                    edtAdress.setText("");
                    edtDNI.setText("");


                    edtDNI.setVisibility(View.INVISIBLE);
                    edtNombre.setVisibility(View.INVISIBLE);
                    edtAdress.setVisibility(View.INVISIBLE);
                    edtMail.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);

                } else {
                    if (nombre.length() == 0) {
                        Toast.makeText(MainActivity.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                    }
                     if (mail.length() == 0) {
                        Toast.makeText(MainActivity.this, "Ingrese un mail", Toast.LENGTH_SHORT).show();
                    }
                     if (direccion.length() == 0) {
                        Toast.makeText(MainActivity.this, "Ingrese una direccion", Toast.LENGTH_SHORT).show();
                    }
                    if (dni.length() == 0) {
                        Toast.makeText(MainActivity.this, "Ingrese un DNI", Toast.LENGTH_SHORT).show();
                    }



                }


            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "Direccion: " + lvdatos.get(position).getaddress() + " DNI: " + lvdatos.get(position).getdni(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.borrar:
                Toast.makeText(this, "Seleccione el usuario que desea eliminar", Toast.LENGTH_SHORT).show();

                eliminando = true;

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        if(eliminando == true)
                        {
                            lvdatos.remove(position);
                            lv.setAdapter(adaptador);
                            eliminando = false;
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Direccion: " + lvdatos.get(position).getaddress() + " DNI: " + lvdatos.get(position).getdni(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;


            case R.id.editar:

                Toast.makeText(this, "Seleccione el usuario que desea editar", Toast.LENGTH_SHORT).show();

                editando = true;

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if(editando == true)
                        {
                            ActionButton.hide();
                            getSupportActionBar().hide();
                            editando = false;
                            edtDNI.setVisibility(View.VISIBLE);
                            edtNombre.setVisibility(View.VISIBLE);
                            edtAdress.setVisibility(View.VISIBLE);
                            edtMail.setVisibility(View.VISIBLE);
                            lv.setVisibility(View.INVISIBLE);

                            btnAceptar.setVisibility(View.VISIBLE);

                            edtNombre.setText(lvdatos.get(position).getnombre());
                            edtMail.setText(lvdatos.get(position).getmail());
                            edtAdress.setText(lvdatos.get(position).getaddress());
                            edtDNI.setText(lvdatos.get(position).getdni());

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Direccion: " + lvdatos.get(position).getaddress() + " DNI: " + lvdatos.get(position).getdni(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                break;
        }
        return true;
    }

}







