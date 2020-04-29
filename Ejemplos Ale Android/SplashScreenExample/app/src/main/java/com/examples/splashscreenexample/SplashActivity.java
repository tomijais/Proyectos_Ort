package com.examples.splashscreenexample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity
{
    private TimerTask task;
    private Timer timer;
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        task = new TimerTask()
        {
            @Override
            public void run()
            {   // Start the next activity
                Intent Intentprincipal = new Intent (SplashActivity.this,MainActivity.class);
                startActivity(Intentprincipal);
                finish();
            }
        };

        timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);


    }
}

