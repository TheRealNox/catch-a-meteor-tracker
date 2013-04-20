package com.nox.catch_a_meteor.db.util;

import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.model.*;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCustomCursorAdapter  extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    public MyCustomCursorAdapter(Context context, Cursor c) {
        super(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
        mLayoutInflater = LayoutInflater.from(context); 
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.calendar_entry, parent, false);
        return v;
    }

	@Override
	public void bindView(View v, Context con, Cursor c) {
		String name = c.getString(c.getColumnIndexOrThrow("name"));
//		String date = c.getString(c.getColumnIndexOrThrow(""));
//		String emailList = c.getString(c.getColumnIndexOrThrow(""));
		
		TextView eventName = (TextView) v.findViewById(R.id.calendarEntryName);
        if (eventName != null) {
        	eventName.setText(name);
        }

//        TextView eventDate = (TextView) v.findViewById(R.id.eventEntryDate);
//        if (eventDate != null) {
//        	eventDate.setText(date);
//        }
//		TextView eventAttendeesNbr = (TextView) v.findViewById(R.id.eventEntryAttendeesNbr);
//        if (eventAttendeesNbr != null) {
//        	String tokens[] = emailList.split(";");
//        	eventAttendeesNbr.setText(tokens.length + " invitee" + (tokens.length > 1 ? "s":""));
//        }
        
	}
}
