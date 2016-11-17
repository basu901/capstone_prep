package com.example.shaunakbasu.capstone;


import android.content.Intent;
import android.database.Cursor;

import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shaunakbasu.capstone.adapters.RoutineParentAdapter;
import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;
import com.example.shaunakbasu.capstone.data.RoutineDetailsProvider;

public class RoutineFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageButton add_routine;
    private static int LOADER_ROUTINE_ITEMS=0;
    RoutineParentAdapter routineParentAdapter;
    View rootView;
    ListView routine_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        rootView=inflater.inflate(R.layout.routine_maker,container,false);

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Cursor cursor=getActivity().getContentResolver().query(RoutineDetailsProvider.Routine.CONTENT_URI,new String[]{"DISTINCT "+RoutineDetailsColumns.ROUTINE_HEADER},null,null,null);
        /*if(cursor!=null) {
            cursor.moveToFirst();
            Log.v("IN RFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.ROUTINE_HEADER)));
            while(cursor.moveToNext()) {
                //Log.v("IN RFFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns._ID)));
                Log.v("IN RFFF : ", cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.ROUTINE_HEADER)));
            }
        }*/

        if(cursor!=null&&cursor.getCount()>0){
            cursor.moveToFirst();
            getLoaderManager().initLoader(LOADER_ROUTINE_ITEMS, null, this);
            routineParentAdapter=new RoutineParentAdapter(getActivity().getApplicationContext(),null,0);
            routine_list= (ListView) rootView.findViewById(R.id.routine_list);
            routine_list.setAdapter(routineParentAdapter);
            routine_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.v("IN ON ITEM CLICK:","!!!!!");
                    String routine_header = ((TextView) view.findViewById(R.id.routine_header_text)).getText().toString();
                    Intent intent = new Intent(getActivity().getApplicationContext(), RoutineDisplay.class);
                    intent.putExtra("routine_header", routine_header);
                    startActivity(intent);
                }
            });
            cursor.close();
        }

        add_routine=(ImageButton)rootView.findViewById(R.id.routine_add_button);
        add_routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(),RoutineDetailActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = RoutineDetailsColumns._ID + " IS NOT NULL" + ") GROUP BY ( " + RoutineDetailsColumns.ROUTINE_HEADER;
        return new CursorLoader(getActivity().getApplicationContext(), RoutineDetailsProvider.Routine.CONTENT_URI,
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
