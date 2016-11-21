package com.example.shaunakbasu.capstone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.capstone.adapters.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 13-11-2016.
 */
public class MainDisplayActivity extends AppCompatActivity {

    ArrayList<NavItems> navItemsArrayList;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences user_preferences=getSharedPreferences(getResources().getString(R.string.user_shared_pref), Context.MODE_PRIVATE);
        boolean has_logged=user_preferences.getBoolean(getResources().getString(R.string.logged_in),false);

       /* if(!has_logged){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }*/
       // else {
            setContentView(R.layout.activity_main);
            toolbar=(Toolbar)findViewById(R.id.main_toolbar);

            setSupportActionBar(toolbar);

            drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
            navigationView=(NavigationView)findViewById(R.id.nav_view);


           /* navItemsArrayList = new ArrayList<>();

            String weight = user_preferences.getString(getResources().getString(R.string.user_weight), getResources().getString(R.string.not_filled));
            String height = user_preferences.getString(getResources().getString(R.string.user_height), getResources().getString(R.string.not_filled));
            String dob = user_preferences.getString(getResources().getString(R.string.user_dob), getResources().getString(R.string.not_filled));


            float val = (Float.parseFloat(weight) / Float.parseFloat(height)) / Float.parseFloat(height);

            SharedPreferences.Editor editor = user_preferences.edit();
            editor.putString(getResources().getString(R.string.user_bmi), Float.toString(val));
            editor.apply();


            NavItems n_dob = new NavItems(getResources().getString(R.string.dob_small), dob, R.drawable.ic_dob);
            NavItems n_weight = new NavItems(getResources().getString(R.string.weight_small), weight, R.drawable.ic_weight);
            NavItems n_height = new NavItems(getResources().getString(R.string.height_small), height, R.drawable.ic_height);
            NavItems n_bmi = new NavItems(getResources().getString(R.string.bmi), Float.toString(val) + " " + Utility.getBMIDetails(val), R.drawable.ic_bmi);

            navItemsArrayList.add(n_dob);
            navItemsArrayList.add(n_weight);
            navItemsArrayList.add(n_height);
            navItemsArrayList.add(n_bmi);*/

            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

            tabLayout.addTab(tabLayout.newTab().setText("Run"));
            tabLayout.addTab(tabLayout.newTab().setText("Stopwatch"));
            tabLayout.addTab(tabLayout.newTab().setText("Routine"));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        Menu nav_menu=navigationView.getMenu();

        MenuItem menu_weight=nav_menu.findItem(R.id.nav_weight);
        View view_weight= MenuItemCompat.getActionView(menu_weight);
        ImageView weight_image=(ImageView)view_weight.findViewById(R.id.nav_image_menu);
        weight_image.setImageResource(R.drawable.ic_weight_18dp);
        TextView weight_label=(TextView)view_weight.findViewById(R.id.nav_text_label_menu);
        weight_label.setText(getResources().getString(R.string.weight_small));
        TextView weight_value=(TextView)view_weight.findViewById(R.id.nav_text_edit_menu);
        weight_value.setText("45.0");

        MenuItem menu_height=nav_menu.findItem(R.id.nav_height);
        View view_height= MenuItemCompat.getActionView(menu_height);
        ImageView height_image=(ImageView)view_height.findViewById(R.id.nav_image_menu);
        height_image.setImageResource(R.drawable.ic_height_18dp);
        TextView height_label=(TextView)view_height.findViewById(R.id.nav_text_label_menu);
        height_label.setText(getResources().getString(R.string.height_small));
        TextView height_value=(TextView)view_height.findViewById(R.id.nav_text_edit_menu);
        height_value.setText("1.8");

        MenuItem menu_dob=nav_menu.findItem(R.id.nav_dob);
        View view_dob= MenuItemCompat.getActionView(menu_dob);
        ImageView dob_image=(ImageView)view_dob.findViewById(R.id.nav_image_menu);
        dob_image.setImageResource(R.drawable.ic_date_of_birth_18dp);
        TextView dob_label=(TextView)view_dob.findViewById(R.id.nav_text_label_menu);
        dob_label.setText(getResources().getString(R.string.dob_small));
        TextView dob_value=(TextView)view_dob.findViewById(R.id.nav_text_edit_menu);
        dob_value.setText("12/12/1992");

        MenuItem menu_bmi=nav_menu.findItem(R.id.nav_bmi);
        View view_bmi= MenuItemCompat.getActionView(menu_bmi);
        ImageView bmi_image=(ImageView)view_bmi.findViewById(R.id.nav_image_menu);
        bmi_image.setImageResource(R.drawable.ic_bmi_18dp);
        TextView bmi_label=(TextView)view_bmi.findViewById(R.id.nav_text_label_menu);
        bmi_label.setText(getResources().getString(R.string.bmi_small));
        TextView bmi_value=(TextView)view_bmi.findViewById(R.id.nav_text_edit_menu);
        bmi_value.setText("24.0 Normal");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    }

//}
