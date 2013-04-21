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
// file: Calendar.java
// created: Apr 21, 2013
//

package com.nox.catch_a_meteor.activities;

import java.sql.SQLException;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.nox.catch_a_meteor.IntentHelper;
import com.nox.catch_a_meteor.db.util.MyCustomCursorAdapter;
import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.dao.DatabaseHelper;
import com.nox.catch_a_meteor.dao.DatabaseLoader;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Calendar extends Activity {

	public static final String TAG = "CalendarActivity";

	private MyCustomCursorAdapter mCustomAdapter;
	private Dao<MeteorShowerEvent, Integer> mMeteorShowerEventDao;
	private ListView mMeteorShowerList;

	private DatabaseHelper databaseHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
		mMeteorShowerList = (ListView) findViewById(R.id.calendarList);
		
		try {
			Log.d(TAG, "Database Initialization if needed");

			mMeteorShowerEventDao = getHelper().getMeteorShowerEventDao();
			if (null == mMeteorShowerEventDao.queryForAll() || mMeteorShowerEventDao.queryForAll().size() == 0) {
				DatabaseLoader.LoadMeteorShowers(getHelper());
			}
			
			 AndroidDatabaseResults results = 
			          (AndroidDatabaseResults)mMeteorShowerEventDao.iterator().getRawResults(); 
			 mCustomAdapter = new MyCustomCursorAdapter(this, results.getRawCursor());   
			 mMeteorShowerList.setAdapter(mCustomAdapter);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mMeteorShowerList.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		Log.d(TAG, "mMeteorShowerList item clicked at " + position + " with id:" + id);
	    		launchEventViewer(id);
	    	}
	    });
	
	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidDatabaseResults results = 
		          (AndroidDatabaseResults)mMeteorShowerEventDao.iterator().getRawResults();
		mCustomAdapter.changeCursor(results.getRawCursor());
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "Activity State: onDestroy()");
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
		super.onDestroy();
	}
	
    protected void launchEventViewer(long id) {
    	Log.v(TAG, "launchEventViewer");
    	Intent i = new Intent(this, EventViewer.class);
    	IntentHelper.addObjectForKey(id, "id");
    	startActivity(i);
    }
   public DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return databaseHelper;
	}
}
