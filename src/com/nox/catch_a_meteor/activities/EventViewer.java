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

import com.nox.catch_a_meteor.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class EventViewer extends Activity {
	public static final String TAG = "EventViewerActivity";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "Activity State: onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_viewer);
	}
}
