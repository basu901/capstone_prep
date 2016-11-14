package com.example.shaunakbasu.capstone;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;
import com.example.shaunakbasu.capstone.data.RoutineDetailsProvider;

/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineDetailActivity extends AppCompatActivity {

    EditText work_out_text;
    EditText routine_details_header;
    ImageButton done_button;
    ImageButton add_button;
    LinearLayout check_box_container;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_detail);
        work_out_text=(EditText)findViewById(R.id.work_out_text);
        done_button=(ImageButton)findViewById(R.id.routine_done_button);
        add_button=(ImageButton)findViewById(R.id.routine_item_add_button);
        routine_details_header=(EditText)findViewById(R.id.routine_details_header);
        check_box_container=(LinearLayout)findViewById(R.id.linear_checkbox_container);;
    }

    @Override
    protected void onStart(){
        super.onStart();


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri id;
                long confirm;
                int i=0;
                if(work_out_text.getText()!=null){
                    AppCompatCheckBox checkBox=new AppCompatCheckBox(getApplicationContext());
                    checkBox.setText(work_out_text.getText());
                    checkBox.setId(i);
                    i++;
                    check_box_container.addView(checkBox);
                    //check_box_container.setLayoutParams(new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    if(routine_details_header.getText()!=null){
                        ContentValues routine_values=new ContentValues();
                        routine_values.put(RoutineDetailsColumns.CHECKED,"false");
                        routine_values.put(RoutineDetailsColumns.ROUTINE_HEADER,routine_details_header.getText().toString());
                        routine_values.put(RoutineDetailsColumns.WORK_OUT_DETAILS,work_out_text.getText().toString());
                        id=getContentResolver().insert(RoutineDetailsProvider.Routine.CONTENT_URI,routine_values);
                        confirm= ContentUris.parseId(id);
                        if(confirm>0)
                            Snackbar.make(view,"INSERTED!",Snackbar.LENGTH_SHORT).show();
                    }
                    else{
                        Snackbar.make(view,"FILL IN THE ROUTINE HEADER",Snackbar.LENGTH_SHORT).show();
                    }
                }
                else{
                    Snackbar.make(view,"THERE IS NO WORKOUT DETAILS!",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri id;
                long confirm;
                if(work_out_text.getText()!=null){
                   // LinearLayout check_box_container=(LinearLayout)view.findViewById(R.id.linear_checkbox_container);
                    //CheckBox checkBox=new CheckBox(getApplicationContext());
                    //checkBox.setText(work_out_text.getText());
                    //check_box_container.addView(checkBox);
                    if(routine_details_header.getText()!=null){
                        ContentValues routine_values=new ContentValues();
                        routine_values.put(RoutineDetailsColumns.CHECKED,"false");
                        routine_values.put(RoutineDetailsColumns.ROUTINE_HEADER,routine_details_header.getText().toString());
                        routine_values.put(RoutineDetailsColumns.WORK_OUT_DETAILS,work_out_text.getText().toString());
                        id=getContentResolver().insert(RoutineDetailsProvider.Routine.CONTENT_URI,routine_values);
                        confirm= ContentUris.parseId(id);
                        if(confirm>0) {
                            Snackbar.make(view, "LAST ITEM INSERTED!", Snackbar.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),RoutineDisplay.class);
                            intent.putExtra("routine_header",routine_details_header.getText().toString());
                            startActivity(intent);
                        }

                    }
                    else{
                        Snackbar.make(view,"FILL IN THE ROUTINE HEADER",Snackbar.LENGTH_SHORT).show();
                    }
                }
                else{
                    Intent intent=new Intent(getApplicationContext(),RoutineDisplay.class);
                    intent.putExtra("routine_header",routine_details_header.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
