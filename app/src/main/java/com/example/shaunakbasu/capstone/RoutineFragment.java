package com.example.shaunakbasu.capstone;


import android.content.Intent;
import android.database.Cursor;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shaunakbasu.capstone.adapters.RoutineParentAdapter;
import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;
import com.example.shaunakbasu.capstone.data.RoutineDetailsProvider;

public class RoutineFragment extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageButton add_routine;
    private static int LOADER_ROUTINE_ITEMS=0;
    RoutineParentAdapter routineParentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_maker);
        Cursor cursor=getContentResolver().query(RoutineDetailsProvider.Routine.CONTENT_URI,new String[]{"DISTINCT "+RoutineDetailsColumns.ROUTINE_HEADER},null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            Log.v("IN RFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.ROUTINE_HEADER)));
            while(cursor.moveToNext()) {
                //Log.v("IN RFFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns._ID)));
                Log.v("IN RFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.ROUTINE_HEADER)));
            }
        }

        if(cursor.moveToFirst()){
            getSupportLoaderManager().initLoader(LOADER_ROUTINE_ITEMS, null, this);
            routineParentAdapter=new RoutineParentAdapter(getApplicationContext(),null,0);
            final ListView routine_list = (ListView) findViewById(R.id.routine_list);
            routine_list.setAdapter(routineParentAdapter);
            routine_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.v("IN ON ITEM CLICK:","!!!!!");
                    String routine_header = ((TextView) view.findViewById(R.id.routine_header_text)).getText().toString();
                    Intent intent = new Intent(getApplicationContext(), RoutineDisplay.class);
                    intent.putExtra("routine_header", routine_header);
                    startActivity(intent);
                }
            });
        }
        add_routine=(ImageButton)findViewById(R.id.routine_add_button);

    }

    @Override
    protected void onStart(){
        super.onStart();
        add_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RoutineDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = RoutineDetailsColumns._ID + " IS NOT NULL" + ") GROUP BY ( " + RoutineDetailsColumns.ROUTINE_HEADER;
        return new CursorLoader(getApplicationContext(), RoutineDetailsProvider.Routine.CONTENT_URI,
                new String[]{"DISTINCT "+RoutineDetailsColumns.ROUTINE_HEADER,RoutineDetailsColumns._ID},selection,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        routineParentAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        routineParentAdapter.swapCursor(null);
    }


}
