package com.examples.recyclerviewexample.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.recyclerviewexample.Adapters.SampleAdapter;
import com.examples.recyclerviewexample.Models.SampleItem;
import com.examples.recyclerviewexample.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private SampleAdapter sampleAdapter;
    private ArrayList<SampleItem> sampleDataList = new ArrayList<SampleItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec);
        
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        sampleDataList.add(new SampleItem("item1"));
        sampleDataList.add(new SampleItem("item2"));
        sampleDataList.add(new SampleItem("item3"));
        sampleDataList.add(new SampleItem("item4"));
        sampleDataList.add(new SampleItem("item5"));
        sampleDataList.add(new SampleItem("item6"));
        sampleDataList.add(new SampleItem("item7"));
        sampleDataList.add(new SampleItem("item8"));
        sampleDataList.add(new SampleItem("item9"));
        sampleDataList.add(new SampleItem("item10"));


        sampleAdapter = new SampleAdapter(sampleDataList,this);
        recyclerView.setAdapter(sampleAdapter);

    }
}
