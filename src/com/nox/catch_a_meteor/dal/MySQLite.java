package com.nox.catch_a_meteor.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MySQLite extends SQLiteOpenHelper {
	 
		public static final String COL_ID = "_id";
		public static final String COL_NAME = "Name";
		public static final String COL_DATE = "Date";
		public static final String COL_DATE_INT = "Date_int";
		public static final String COL_VENUE = "Venue";
		public static final String COL_NOTE = "Note";
		public static final String COL_ATTENDEES = "Attendees";
	 
		private static final String CREATE_BDD = "CREATE TABLE if not exists " + UserDao.TABLE + " ("
		+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, " + COL_DATE + " TEXT NOT NULL, " + COL_DATE_INT + " SIGNED BIGINT, "
		+ COL_VENUE + " TEXT NOT NULL, " + COL_NOTE + " TEXT NOT NULL, "
		+ COL_ATTENDEES + " LONGTEXT NOT NULL );";
	 
		public MySQLite(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
	 
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_BDD);
		}
	 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(MySQLite.class.getName(),
		            "Upgrading database from version " + oldVersion + " to "
		                + newVersion + ", which will destroy all old data");
		        db.execSQL("DROP TABLE IF EXISTS " + UserDao.TABLE);
		        onCreate(db);
		}
}
