package com.example.shaunakbasu.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText first_name,last_name,dob,weight,height;
    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent=new Intent(getApplicationContext(),RoutineFragment.class);
                startActivity(intent);
            }
        });

    }
}
