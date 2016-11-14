package com.example.shaunakbasu.capstone.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaunakbasu.capstone.R;
import com.example.shaunakbasu.capstone.RoutineParent;
import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;

import java.util.ArrayList;

/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineParentAdapter extends CursorAdapter {

    public static class ViewHolder {

        public final TextView routine_header;

        public ViewHolder(View view) {
            routine_header=(TextView)view.findViewById(R.id.routine_header_text);
        }
    }

    public RoutineParentAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int layoutId = R.layout.routine_item;

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.routine_header.setText(cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.ROUTINE_HEADER)));

    }


}