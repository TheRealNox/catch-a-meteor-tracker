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
import java.util.Collection;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.nox.catch_a_meteor.db.util.MyCustomCursorAdapter;
import com.nox.catch_a_meteor.R;
import com.nox.catch_a_meteor.dao.DatabaseHelper;
import com.nox.catch_a_meteor.dao.DatabaseLoader;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;
import com.nox.catch_a_meteor.model.User;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class Calendar extends Activity {

	public static final String TAG = "CalendarActivity";

	private DatabaseHelper databaseHelper = null;
	private MyCustomCursorAdapter mCustomAdapter;
	private Dao<MeteorShowerEvent, Integer> mMeteorShowerEventDao;
	private ListView mMeteorShowerList;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
		mMeteorShowerList = (ListView) findViewById(R.id.calendarList);
		
		try {
			Log.d(TAG, "Database Initialization if needed");
			DatabaseLoader.CreateSchema(getHelper());

			mMeteorShowerEventDao = getHelper().getMeteorShowerEventDao();
			
			Dao<User, String> userDao = getHelper().getUserDao();
			/*
			 * User user = new User("gprevost", "Guillaume", "Prevost", new
			 * ArrayList<SpaceObjectObservation>()); userDao.create(user);
			 */

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
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

  private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}
}
