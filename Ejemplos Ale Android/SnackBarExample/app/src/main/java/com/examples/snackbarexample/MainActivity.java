package com.examples.snackbarexample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


/**
 * Ejemplo de uso de Snackbar
 *
 * Tener en cuenta que el layout debe ser CoordinatorLayout
 *
 */

public class MainActivity extends AppCompatActivity
{

    public Button btnSimpleSnack;
    public Button btnActionSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActionSnack = findViewById(R.id.btn_action_snack);
        btnSimpleSnack = findViewById(R.id.btn_simple_snack);

        btnSimpleSnack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Snackbar snackbar = Snackbar
                        .make(v, "Mensaje", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });

        btnActionSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(v, "Mensaje Borrado", Snackbar.LENGTH_LONG)
                        .setAction("DESHACER", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                Snackbar snackbar1 = Snackbar.make(v, "Mensaje recuperado!", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();
            }

        });

    }
}
