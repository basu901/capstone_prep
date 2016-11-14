package com.example.shaunakbasu.capstone.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.shaunakbasu.capstone.R;
import com.example.shaunakbasu.capstone.data.RoutineDetailsColumns;


/**
 * Created by shaunak basu on 11-11-2016.
 */
public class RoutineChildAdapter extends CursorAdapter {

    public static class ViewHolder {
        public final CheckBox checkBox;
        public final TextView work_out_details;

        public ViewHolder(View view) {
            checkBox=(CheckBox)view.findViewById(R.id.routine_checkbox);
            work_out_details=(TextView)view.findViewById(R.id.routine_detail_text);
        }
    }

    public RoutineChildAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int layoutId = R.layout.routine_detail_item;

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.work_out_details.setText(cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.WORK_OUT_DETAILS)));
        String check=cursor.getString(cursor.getColumnIndex(RoutineDetailsColumns.CHECKED));
        if(check.equals("true"))
            viewHolder.checkBox.setChecked(true);
        else
            viewHolder.checkBox.setChecked(false);

    }


}
