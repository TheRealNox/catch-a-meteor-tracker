//
// International Space Apps Challenge
//
// Catch A Meteor
// 		url: https://code.google.com/p/catch-a-meteor-tracker/
// 		made by:
//
// 			+ Aubry Nicolas (nox.aubry@gmail.com)
// 			+ Prevost Guillaume (guillaume.prevost.gp@gmail.com)
//
// file: EventViewer.java
// created: Apr 21, 2013
//


package com.nox.catch_a_meteor.activities;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.nox.catch_a_meteor.IntentHelper;
import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.dao.DatabaseHelper;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class EventViewer extends Activity {
	
	public static final String TAG = "EventViewerActivity";
	
	private DatabaseHelper mDatabaseHelper = null;
	private long mID;
	private MeteorShowerEvent mEvent;
	
	private TextView mName;
	private TextView mDescription;
	private TextView mParent;
	private TextView mFull;
	private TextView mPeak;
	private TextView mVelocity;
	private TextView mRadiantRight;
	private TextView mRadiantDepth;
	private TextView mSolar;
	private TextView mZentih;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_viewer);
		mID = (Long) IntentHelper.getObjectForKey("id");
		int id = (int)mID;
		mDatabaseHelper = getHelper();
		try {
			mEvent = mDatabaseHelper.getMeteorShowerEventDao().queryForId(id);
		} catch (SQLException e) {
			Log.d(TAG, TAG + " SQLException : " + e.getMessage());
			e.printStackTrace();
		}

		mName = (TextView) findViewById(R.id.calendarViewName);
		mDescription = (TextView) findViewById(R.id.calendarViewDescription);
		mParent = (TextView) findViewById(R.id.calendarViewParent);
		mFull = (TextView) findViewById(R.id.calendarViewFull);
		mPeak = (TextView) findViewById(R.id.calendarViewPeak);
		mVelocity = (TextView) findViewById(R.id.calendarViewVelocity);
		mRadiantRight = (TextView) findViewById(R.id.calendarViewRadiantRight);
		mRadiantDepth = (TextView) findViewById(R.id.calendarViewRadiantDepth);
		mSolar = (TextView) findViewById(R.id.calendarViewSolar);
		mZentih = (TextView) findViewById(R.id.calendarViewZenith);

		SimpleDateFormat formatDate = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

		mName.setText(mEvent.getName());

		int moon = mEvent.getMoon();
		
        if (mName != null) {
        	switch (moon) {
	        	case (0) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m0, 0, 0, 0);
	        		break;
	        	case (1) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m1, 0, 0, 0);
	        		break;
	        	case (2) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m2, 0, 0, 0);
	        		break;
	        	case (3) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m3, 0, 0, 0);
	        		break;
	        	case (4) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m4, 0, 0, 0);
	        		break;
	        	case (5) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m5, 0, 0, 0);
	        		break;
	        	case (6) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m6, 0, 0, 0);
	        		break;
	        	case (7) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m7, 0, 0, 0);
	        		break;
	        	case (8) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m8, 0, 0, 0);
	        		break;
	        	case (9) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m9, 0, 0, 0);
	        		break;
	        	case (10) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m10, 0, 0, 0);
	        		break;
	        	case (11) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m11, 0, 0, 0);
	        		break;
	        	case (12) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m12, 0, 0, 0);
	        		break;
	        	case (13) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m13, 0, 0, 0);
	        		break;
	        	case (14) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m14, 0, 0, 0);
	        		break;
	        	case (15) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m15, 0, 0, 0);
	        		break;
	        	case (16) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m16, 0, 0, 0);
	        		break;
	        	case (17) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m17, 0, 0, 0);
	        		break;
	        	case (18) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m18, 0, 0, 0);
	        		break;
	        	case (19) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m19, 0, 0, 0);
	        		break;
	        	case (20) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m20, 0, 0, 0);
	        		break;
	        	case (21) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m21, 0, 0, 0);
	        		break;
	        	case (22) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m22, 0, 0, 0);
	        		break;
	        	case (23) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m23, 0, 0, 0);
	        		break;
	        	case (24) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m24, 0, 0, 0);
	        		break;
	        	case (25) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m25, 0, 0, 0);
	        		break;
	        	case (26) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m26, 0, 0, 0);
	        		break;
	        	case (27) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m27, 0, 0, 0);
	        		break;
	        	case (28) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m28, 0, 0, 0);
	        		break;
	        	case (29) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m29, 0, 0, 0);
	        		break;
	        	case (30) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m30, 0, 0, 0);
	        		break;
	        	case (31) :
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m31, 0, 0, 0);
	        		break;
	        	default:
	        		mName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.m0, 0, 0, 0);
        	}
        }
		
		
		mDescription.setText("Description: " + mEvent.getDescription());
		mParent.setText("Parent Object: " + mEvent.getParentObject());
		mPeak.setText("Peak Date: " + formatDate.format(mEvent.getPeakDate()));
		String tmp = String.format("%f", mEvent.getVelocity());
		mVelocity.setText("Velocity: " + tmp);
		mRadiantRight.setText("Radiant Right Ascension: " + mEvent.getRadiantRightAscension());
		mRadiantDepth.setText("Radiant Declinaison: " + mEvent.getRadiantDeclinaison());
		tmp = String.format("%f", mEvent.getSolarLongitude());
		mSolar.setText("Solar Longitude: " + tmp);
		mZentih.setText("Zenith Hourly Rate: " + mEvent.getZenithHourlyRate());
		mFull.setText("From " + formatDate.format(mEvent.getStartDate()) + " to " + formatDate.format(mEvent.getEndDate()));
		
	}

	public DatabaseHelper getHelper() {
		if (mDatabaseHelper == null) {
			mDatabaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return mDatabaseHelper;
	}
}