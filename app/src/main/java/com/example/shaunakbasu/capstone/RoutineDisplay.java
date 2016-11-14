package com.example.shaunakbasu.capstone;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shaunakbasu.capstone.adapters.RoutineChildAdapter;
import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;
import com.example.shaunakbasu.capstone.data.RoutineDetailsProvider;

/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineDisplay extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ITEM_DISPLAY_LOADER=1;
    String routine_header;
    RoutineChildAdapter routineChildAdapter;
    ListView list_routine_display;
    TextView routine_display_header;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        routine_header=getIntent().getStringExtra("routine_header");
        setContentView(R.layout.routine_display);
        getSupportLoaderManager().initLoader(ITEM_DISPLAY_LOADER,null,this);
        routine_display_header=(TextView)findViewById(R.id.routine_display_header);
        routine_display_header.setText(routine_header);
        routineChildAdapter=new RoutineChildAdapter(getApplicationContext(),null,0);
        list_routine_display=(ListView)findViewById(R.id.list_routine_display);
        list_routine_display.setAdapter(routineChildAdapter);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(), RoutineDetailsProvider.Routine.CONTENT_URI,
                new String[]{RoutineDetailsColumns._ID,RoutineDetailsColumns.WORK_OUT_DETAILS, RoutineDetailsColumns.CHECKED},
                RoutineDetailsColumns.ROUTINE_HEADER + " =?",
                new String[]{routine_header},null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        routineChildAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        routineChildAdapter.swapCursor(null);
    }
}
