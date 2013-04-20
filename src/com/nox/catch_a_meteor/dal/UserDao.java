package com.nox.catch_a_meteor.dal;

import com.nox.catch_a_meteor.model.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "catch_a_meteor.db";

	public static final String TABLE = "user";
	public static final String COL_ID = "_id";
	public static final int NUM_COL_ID = 0;
	public static final String COL_FIRSTNAME = "firstname";
	public static final int NUM_COL_FIRSTNAME = 1;
	public static final String COL_LASTNAME = "lastname";
	public static final int NUM_COL_LASTNAME = 2;
	public static final String COL_USERNAME = "username";
	public static final int NUM_COL_USERNAME = 3;

	private SQLiteDatabase db;

	private MySQLite mySQLite;
	
	private String[] allColumns = {
			COL_ID,
			COL_FIRSTNAME };

	public UserDao(Context context){
		mySQLite = new MySQLite(context, DB_NAME, null, DB_VERSION);
	}

	public void open(){
		db = mySQLite.getWritableDatabase();
	}

	public void close(){
		db.close();
	}

	public SQLiteDatabase getBDD(){
		return db;
	}

	public long insertEvent(User user){
		ContentValues values = new ContentValues();
		values.put(COL_FIRSTNAME, user.getFirstname());
		values.put(COL_LASTNAME, user.getLastname());
		values.put(COL_USERNAME, user.getUsername());
		return db.insert(TABLE, null, values);
	}

	public long createUser(String firstname, String lastname, String username){
		ContentValues values = new ContentValues();

		values.put(COL_FIRSTNAME, firstname);
		values.put(COL_LASTNAME, lastname);
		values.put(COL_USERNAME, username);
		return db.insert(TABLE, null, values);
	}
	
	public int updateEvent(long mID, User user){
		ContentValues values = new ContentValues();
		values.put(COL_FIRSTNAME, user.getFirstname());
		values.put(COL_LASTNAME, user.getLastname());
		values.put(COL_USERNAME, user.getUsername());
		return db.update(TABLE, values, COL_ID + " = " +mID, null);
	}

	public int removeUserWithID(long id){
		return db.delete(TABLE, COL_ID + " = " +id, null);
	}
	
	public User getUserFromId(long mID){
		Cursor c = db.query(TABLE, allColumns, COL_ID + " LIKE \"" + mID +"\"", null, null, null, null);
		return cursorToUser(c);
	}

	public User getUserFromUsername(String name){
		Cursor c = db.query(TABLE, allColumns, COL_USERNAME + " = \"" + name +"\"", null, null, null, null);
		return cursorToUser(c);
	}
	
	public User getUserLikeUsername(String name){
		Cursor c = db.query(TABLE, allColumns, COL_USERNAME + " LIKE \"" + name +"\"", null, null, null, null);
		return cursorToUser(c);
	}
	
	public List<User> getAllEvents() {
		List<User> users = new ArrayList<User>();
		
		Cursor cursor = db.query(TABLE, allColumns, null, null, null, null, COL_USERNAME + " ASC");
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user= cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		cursor.close();
		return users;
	}
	
	public Cursor getAllUsersOnCursor() {

		Cursor cursor = db.query(TABLE, allColumns, null, null, null, null, COL_USERNAME + " ASC");
		
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		return cursor;
	}
	
	public String getColUsername() {
		return COL_USERNAME;
	}

	private User cursorToUser(Cursor c){
		if (c.getCount() == 0)
			return null;

		c.moveToFirst();
		User user = new User();
		user.setId(c.getInt(NUM_COL_ID));
		user.setFirstname(c.getString(NUM_COL_FIRSTNAME));
		c.close();
		return user;
	}
}