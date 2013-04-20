package com.nox.catch_a_meteor.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.nox.catch_a_meteor.model.SpaceObjectObservation;
import com.nox.catch_a_meteor.model.User;

/**
 * Database helper class used to manage the creation and upgrading of your database. 
 * This class also usually provides the DAOs used by the other classes.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

	private static final Class<?>[] classes = new Class[] {
		User.class,
		SpaceObjectObservation.class
	};
	
	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt", classes);
	}
}
