package com.androidExamples.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.androidExamples.eventbus.Entities.Usuario;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity
{

    private TextView txtCartel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtCartel = findViewById(R.id.txtCartel);

    }

    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(sticky = true , threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageEvent(Usuario user)
    {
        EventBus.getDefault().removeAllStickyEvents();
        txtCartel.setText(user.getUser() +" "+ user.getPassword());
    };


}

