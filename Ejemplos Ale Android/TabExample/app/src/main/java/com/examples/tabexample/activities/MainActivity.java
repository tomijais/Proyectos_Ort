package com.examples.tabexample.activities;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;




import com.examples.tabexample.R;
import com.examples.tabexample.adapters.ViewPagerAdapter;
import com.examples.tabexample.fragments.Tab1Fragment;
import com.examples.tabexample.fragments.Tab2Fragment;
import com.examples.tabexample.fragments.Tab3Fragment;

public class MainActivity extends AppCompatActivity
{

    public ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.example_tabs);
        viewPager = (ViewPager) findViewById(R.id.example_pager);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager()); //Usar Child!

        adapter.addFragment(new Tab1Fragment(),"Tab1");
        adapter.addFragment(new Tab2Fragment(),"Tab2");
        adapter.addFragment(new Tab3Fragment(),"Tab3");
        viewPager.setAdapter(adapter);
    }
}

