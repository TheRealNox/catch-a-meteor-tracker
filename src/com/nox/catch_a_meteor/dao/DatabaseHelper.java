package com.nox.catch_a_meteor.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;
import com.nox.catch_a_meteor.model.SpaceObjectObservation;
import com.nox.catch_a_meteor.model.User;

/**
 * Database helper class used to manage the creation and upgrading of your database. 
 * This class also usually provides the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "catch_a_meteor.db";
	private static final int DATABASE_VERSION = 1;

	private Dao<User, Integer> userDao = null;
	private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
	private Dao<SpaceObjectObservation, Integer> spaceObjectObservationDao = null;
	private RuntimeExceptionDao<SpaceObjectObservation, Integer> spaceObjectObservationRuntimeDao = null;
	private Dao<MeteorShowerEvent, Integer> meteorShowerEventDao = null;
	private RuntimeExceptionDao<MeteorShowerEvent, Integer> meteorShowerEventRuntimeDao = null;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, SpaceObjectObservation.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, SpaceObjectObservation.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Returns the Database Access Object (DAO) for User class. 
	 * It will create it or just give the cached value.
	 */
	public Dao<User, Integer> getUserDao() throws SQLException {
		if (userDao == null) {
			userDao = getDao(User.class);
		}
		return userDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for User class. 
	 * It will create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<User, Integer> getUserRuntimeDao() {
		if (userRuntimeDao == null) {
			userRuntimeDao = getRuntimeExceptionDao(User.class);
		}
		return userRuntimeDao;
	}

	/**
	 * Returns the Database Access Object (DAO) for SpaceObjectObservation class. 
	 * It will create it or just give the cached value.
	 */
	public Dao<SpaceObjectObservation, Integer> getSpaceObjectObservationDao() throws SQLException {
		if (spaceObjectObservationDao == null) {
			spaceObjectObservationDao = getDao(SpaceObjectObservation.class);
		}
		return spaceObjectObservationDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for SpaceObjectObservation class. 
	 * It will create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<SpaceObjectObservation, Integer> getSpaceObjectObservationRuntimeDao() {
		if (spaceObjectObservationRuntimeDao == null) {
			spaceObjectObservationRuntimeDao = getRuntimeExceptionDao(SpaceObjectObservation.class);
		}
		return spaceObjectObservationRuntimeDao;
	}
	
	/**
	 * Returns the Database Access Object (DAO) for SpaceObjectObservation class. 
	 * It will create it or just give the cached value.
	 */
	public Dao<MeteorShowerEvent, Integer> getMeteorShowerEventDao() throws SQLException {
		if (meteorShowerEventDao == null) {
			meteorShowerEventDao = getDao(MeteorShowerEvent.class);
		}
		return meteorShowerEventDao;
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for SpaceObjectObservation class. 
	 * It will create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<MeteorShowerEvent, Integer> getMeteorShowerEventRuntimeDao() {
		if (meteorShowerEventRuntimeDao == null) {
			meteorShowerEventRuntimeDao = getRuntimeExceptionDao(MeteorShowerEvent.class);
		}
		return meteorShowerEventRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		spaceObjectObservationDao = null;
		spaceObjectObservationRuntimeDao = null;
		userDao = null;
		userRuntimeDao = null;
		meteorShowerEventDao = null;
		meteorShowerEventRuntimeDao = null;
	}
}
