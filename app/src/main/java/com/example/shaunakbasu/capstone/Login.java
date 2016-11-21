package com.example.shaunakbasu.capstone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Login extends AppCompatActivity {

    EditText first_name_text,last_name_text,dob_text,weight_text,height_text;
    TextView f_name_t_view,l_name_t_view,dob_t_view,weight_t_view,height_t_view;
    String first_name,last_name,dob,weight,height;
    Button begin;
    boolean checker;
    StringBuffer faulty;
    final static String DATE_FORMAT = "MM-dd-yyyy";
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        f_name_t_view=(TextView)findViewById(R.id.first_name);
        l_name_t_view=(TextView)findViewById(R.id.last_name);
        dob_t_view=(TextView)findViewById(R.id.dob);
        weight_t_view=(TextView)findViewById(R.id.weight);
        height_t_view=(TextView)findViewById(R.id.height);

        faulty=new StringBuffer();

        first_name_text=(EditText)findViewById(R.id.editText_first_name);

        first_name_text.setNextFocusForwardId(R.id.editText_last_name);


        last_name_text=(EditText)findViewById(R.id.editText_last_name);

        last_name_text.setNextFocusForwardId(R.id.editText_dob);

        dob_text=(EditText)findViewById(R.id.editText_dob);

        dob_text.setNextFocusForwardId(R.id.editText_weight);

        weight_text=(EditText)findViewById(R.id.editText_weight);

        weight_text.setNextFocusForwardId(R.id.editText_height);

        height_text=(EditText)findViewById(R.id.editText_height);



        begin=(Button)findViewById(R.id.login_button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.postDelayed(check_fields,0);

                SharedPreferences user_preferences = getSharedPreferences(getResources().getString(R.string.user_shared_pref), Context.MODE_PRIVATE);
                SharedPreferences.Editor user_edit= user_preferences.edit();

                user_edit.putString(getResources().getString(R.string.user_first_name),first_name);
                user_edit.putString(getResources().getString(R.string.user_last_name),last_name);
                user_edit.putString(getResources().getString(R.string.user_dob),dob);
                user_edit.putString(getResources().getString(R.string.user_weight),weight);
                user_edit.putString(getResources().getString(R.string.user_height),height);
                user_edit.putBoolean(getResources().getString(R.string.logged_in),true);

                user_edit.apply();

                Intent intent=new Intent(getApplicationContext(),MainDisplayActivity.class);
                startActivity(intent);
            }
        });

    }

    public Runnable check_fields= new Runnable() {
        @Override
        public void run() {
            do{
                checker=true;
                first_name=first_name_text.getText().toString();
                last_name=last_name_text.getText().toString();
                dob=dob_text.getText().toString();
                weight=weight_text.getText().toString();
                height=height_text.getText().toString();

                if(!Utility.hasOnlyAlphabets(first_name)){
                    faulty.append(f_name_t_view.getText()+",");
                    checker=false;
                }

                if(!Utility.hasOnlyAlphabets(last_name)){
                    faulty.append(l_name_t_view.getText()+",");
                    checker=false;
                }


                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                try{
                    String[] dob_checker=dob.split("-");
                    int yr=Integer.parseInt(dob_checker[2]);

                    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    dateFormat.setLenient(false);
                    dateFormat.parse(dob);
                    if(yr>year){
                        checker=false;
                        faulty.append(dob_t_view.getText()+",");
                    }

                }catch(Exception e){
                    checker=false;
                    faulty.append(dob_t_view.getText()+",");
                }

                try{
                    float w_checker=Float.parseFloat(weight);
                }catch (Exception e){
                    checker=false;
                    faulty.append(weight_t_view.getText()+",");
                }

                try{
                    float h_checker=Float.parseFloat(height);
                }catch (Exception e){
                    checker=false;
                    faulty.append(height_t_view.getText()+",");
                }



                if(!checker){
                    faulty.delete(faulty.length()-1,faulty.length());
                    Snackbar.make(getWindow().getDecorView().getRootView(),faulty.toString()+getResources().getString(R.string.not_valid),Snackbar.LENGTH_LONG).show();
                    faulty.delete(0,faulty.length());
                }



            }while(!checker);
            handler.removeCallbacks(this);
        }
    };
}

