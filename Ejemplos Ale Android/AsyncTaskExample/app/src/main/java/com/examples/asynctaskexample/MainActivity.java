package com.examples.asynctaskexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    private Button btnTask;
    private ProgressDialog p;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTask = findViewById(R.id.btn_task);


        btnTask.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AsyncTaskExample taskExample = new AsyncTaskExample();
                taskExample.execute();

            }
        });

    }

    /*
    *
    *
    *   params,unit progress,result
    *
    *   Params, the type of the parameters sent to the task upon execution.
        Progress, the type of the progress units published during the background computation.
        Result, the type of the result of the background computation.
    *
    * */

    private class AsyncTaskExample extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            p = new ProgressDialog(MainActivity.this);
            p.setMessage("Por favor espere");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected String doInBackground(Void... params)
        {

            long i;

            for ( i=0; i<1000000000;i++)
            {

            }
            return String.valueOf(i);
        }


        @Override
        protected void onPostExecute(String result)
            {
                super.onPostExecute(result);
                p.dismiss();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

            }
        }
    }


