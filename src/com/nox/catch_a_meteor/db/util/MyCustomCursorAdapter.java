package com.nox.catch_a_meteor.db.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.model.*;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCustomCursorAdapter  extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
	public static final String TAG = "MyCustomCursorAdapter";

    
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
		final SimpleDateFormat formatDate = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		String name = c.getString(c.getColumnIndexOrThrow("name"));
		String peak = c.getString(c.getColumnIndexOrThrow("peakDate"));
		String start = c.getString(c.getColumnIndexOrThrow("startDate"));
		String end = c.getString(c.getColumnIndexOrThrow("endDate"));
		int moon = c.getInt(c.getColumnIndexOrThrow("moon"));
		//		String emailList = c.getString(c.getColumnIndexOrThrow(""));
		
		TextView eventName = (TextView) v.findViewById(R.id.calendarEntryName);
        if (eventName != null) {
        	eventName.setText(name);
        	switch (moon) {
        	case (0) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m0, 0, 0, 0);
        		break;
        	case (1) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m1, 0, 0, 0);
        		break;
        	case (2) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m2, 0, 0, 0);
        		break;
        	case (3) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m3, 0, 0, 0);
        		break;
        	case (4) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m4, 0, 0, 0);
        		break;
        	case (5) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m5, 0, 0, 0);
        		break;
        	case (6) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m6, 0, 0, 0);
        		break;
        	case (7) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m7, 0, 0, 0);
        		break;
        	case (8) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m8, 0, 0, 0);
        		break;
        	case (9) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m9, 0, 0, 0);
        		break;
        	case (10) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m10, 0, 0, 0);
        		break;
        	case (11) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m11, 0, 0, 0);
        		break;
        	case (12) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m12, 0, 0, 0);
        		break;
        	case (13) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m13, 0, 0, 0);
        		break;
        	case (14) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m14, 0, 0, 0);
        		break;
        	case (15) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m15, 0, 0, 0);
        		break;
        	case (16) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m16, 0, 0, 0);
        		break;
        	case (17) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m17, 0, 0, 0);
        		break;
        	case (18) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m18, 0, 0, 0);
        		break;
        	case (19) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m19, 0, 0, 0);
        		break;
        	case (20) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m20, 0, 0, 0);
        		break;
        	case (21) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m21, 0, 0, 0);
        		break;
        	case (22) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m22, 0, 0, 0);
        		break;
        	case (23) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m23, 0, 0, 0);
        		break;
        	case (24) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m24, 0, 0, 0);
        		break;
        	case (25) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m25, 0, 0, 0);
        		break;
        	case (26) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m26, 0, 0, 0);
        		break;
        	case (27) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m27, 0, 0, 0);
        		break;
        	case (28) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m28, 0, 0, 0);
        		break;
        	case (29) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m29, 0, 0, 0);
        		break;
        	case (30) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m30, 0, 0, 0);
        		break;
        	case (31) :
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m31, 0, 0, 0);
        		break;
        	default:
        		eventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m0, 0, 0, 0);
        	}
        }

        TextView eventPeak = (TextView) v.findViewById(R.id.calendarPeakNight);
        if (eventPeak != null) {
        	try {
        		Date date = dateTimeFormatter.parse(peak);
				eventPeak.setText("Peak: " + formatDate.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "In Bind view: " + e.getMessage());
				e.printStackTrace();
			}
        }
        
        TextView eventDuration = (TextView) v.findViewById(R.id.calendarFullNight);
        if (eventDuration != null) {
        	try {
        		Date start_date = dateTimeFormatter.parse(start);
        		Date end_date = dateTimeFormatter.parse(end);
        		eventDuration.setText("from " + formatDate.format(start_date) + " to " + formatDate.format(end_date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "In Bind view: " + e.getMessage());
				e.printStackTrace();
			}
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
