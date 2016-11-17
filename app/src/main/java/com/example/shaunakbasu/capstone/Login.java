package com.example.shaunakbasu.capstone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText first_name,last_name,dob,weight,height;
    Button begin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        first_name=(EditText)findViewById(R.id.editText_first_name);
        last_name=(EditText)findViewById(R.id.editText_last_name);
        dob=(EditText)findViewById(R.id.editText_dob);
        weight=(EditText)findViewById(R.id.editText_weight);
        height=(EditText)findViewById(R.id.editText_height);
        first_name.setNextFocusForwardId(R.id.editText_last_name);
        last_name.setNextFocusForwardId(R.id.editText_dob);
        dob.setNextFocusForwardId(R.id.editText_weight);
        weight.setNextFocusForwardId(R.id.editText_height);

        begin=(Button)findViewById(R.id.login_button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences user_preferences = getSharedPreferences(getResources().getString(R.string.user_shared_pref), Context.MODE_PRIVATE);
                SharedPreferences.Editor user_edit= user_preferences.edit();

                user_edit.putString(getResources().getString(R.string.user_first_name),first_name.getText().toString());
                user_edit.putString(getResources().getString(R.string.user_last_name),last_name.getText().toString());
                user_edit.putString(getResources().getString(R.string.user_dob),dob.getText().toString());
                user_edit.putString(getResources().getString(R.string.user_weight),weight.getText().toString());
                user_edit.putString(getResources().getString(R.string.user_height),height.getText().toString());
                user_edit.putBoolean(getResources().getString(R.string.logged_in),true);

                user_edit.apply();

                Intent intent=new Intent(getApplicationContext(),MainDisplayActivity.class);
                startActivity(intent);
            }
        });

    }
}

