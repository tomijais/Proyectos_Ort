package com.androidExamples.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidExamples.eventbus.Entities.Usuario;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity
{

    private Button btnPost;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPost = findViewById(R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                user = new Usuario("admin","1234");
                EventBus.getDefault().postSticky(user);
                Intent i = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(i);
            }
        });

    }


}
