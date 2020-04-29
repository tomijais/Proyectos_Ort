package com.examples.recyclerviewexample.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.examples.recyclerviewexample.Models.SampleItem;
import com.examples.recyclerviewexample.R;

import java.util.ArrayList;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.SampleHolder>
{
   private ArrayList<SampleItem> sampleDataList = new ArrayList<SampleItem>();
   private Context c;

    public SampleAdapter(ArrayList<SampleItem> sampleDataList, Context c)
    {
        this.sampleDataList = sampleDataList;
        this.c = c;
    }

    @Override
    public SampleHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new SampleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SampleHolder viewHolder, int i)
    {
        final SampleItem sampleItemdata;
        sampleItemdata = sampleDataList.get(i);
        viewHolder.setTitle(sampleItemdata.getName());

        viewHolder.getBtnAccion().setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(c, sampleItemdata.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return sampleDataList.size();
    }


    public class SampleHolder extends RecyclerView.ViewHolder
    {

        private View view;

        private TextView textViewItem;
        private Button btnAccion;

        public SampleHolder(View itemView)
        {
            super(itemView);
            view = itemView;
        }

        public Button getBtnAccion()
        {
            btnAccion = view.findViewById(R.id.btn_item);
            return btnAccion;
        }

        public void setTitle(String title)
        {
            TextView titulo = (TextView) view.findViewById(R.id.txt_item);
            titulo.setText(title);
        }

    }



}
