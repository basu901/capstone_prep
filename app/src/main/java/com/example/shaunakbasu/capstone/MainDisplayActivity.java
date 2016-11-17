package com.example.shaunakbasu.capstone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.example.shaunakbasu.capstone.adapters.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 13-11-2016.
 */
public class MainDisplayActivity extends AppCompatActivity {

    ArrayList<NavItems> navItemsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences user_preferences=getSharedPreferences(getResources().getString(R.string.user_shared_pref), Context.MODE_PRIVATE);
        boolean has_logged=user_preferences.getBoolean(getResources().getString(R.string.logged_in),false);

        if(!has_logged){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);

        navItemsArrayList=new ArrayList<>();

        String weight=user_preferences.getString(getResources().getString(R.string.user_weight),getResources().getString(R.string.not_filled));
        String height=user_preferences.getString(getResources().getString(R.string.user_height),getResources().getString(R.string.not_filled));
        String dob=user_preferences.getString(getResources().getString(R.string.user_dob),getResources().getString(R.string.not_filled));


        float val=(Float.parseFloat(weight)/Float.parseFloat(height))/Float.parseFloat(height);

        SharedPreferences.Editor editor=user_preferences.edit();
        editor.putString(getResources().getString(R.string.user_bmi),Float.toString(val));
        editor.apply();


        NavItems n_dob=new NavItems(getResources().getString(R.string.dob_small),dob,R.drawable.ic_dob);
        NavItems n_weight= new NavItems(getResources().getString(R.string.weight_small),weight,R.drawable.ic_weight);
        NavItems n_height=new NavItems(getResources().getString(R.string.height_small),height,R.drawable.ic_height);
        NavItems n_bmi=new NavItems(getResources().getString(R.string.bmi), Float.toString(val)+" "+Utility.getBMIDetails(val),R.drawable.ic_bmi);

        navItemsArrayList.add(n_dob);
        navItemsArrayList.add(n_weight);
        navItemsArrayList.add(n_height);
        navItemsArrayList.add(n_bmi);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Stopwatch"));
        tabLayout.addTab(tabLayout.newTab().setText("Routine"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
