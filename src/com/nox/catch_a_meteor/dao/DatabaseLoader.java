package com.nox.catch_a_meteor.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.nox.catch_a_meteor.model.MeteorShowerEvent;
import com.nox.catch_a_meteor.model.SpaceObjectObservation;
import com.nox.catch_a_meteor.model.User;
import com.nox.catch_a_meteor.model.MeteorShowerEvent.ShowerClass;

/**
 * Database helper class used to manage the creation and upgrading of your database. 
 * This class also usually provides the DAOs used by the other classes.
 */
public class DatabaseLoader {

	public static void CreateSchemaIfNotExists(DatabaseHelper helper) throws SQLException {
		TableUtils.createTableIfNotExists(new AndroidConnectionSource(helper), User.class);
		TableUtils.createTableIfNotExists(new AndroidConnectionSource(helper), SpaceObjectObservation.class);
		TableUtils.createTableIfNotExists(new AndroidConnectionSource(helper), MeteorShowerEvent.class);
	}

	public static void CreateSchema(DatabaseHelper helper) throws SQLException {
		TableUtils.dropTable(new AndroidConnectionSource(helper), User.class, true);
		TableUtils.createTable(new AndroidConnectionSource(helper), User.class);
		
		TableUtils.createTable(new AndroidConnectionSource(helper), SpaceObjectObservation.class);
		TableUtils.dropTable(new AndroidConnectionSource(helper), User.class, true);
		
		TableUtils.createTable(new AndroidConnectionSource(helper), MeteorShowerEvent.class);
		TableUtils.dropTable(new AndroidConnectionSource(helper), User.class, true);
	}
	
	public static void LoadObservationExample (DatabaseHelper helper) throws SQLException {
		Dao<User, String> userDao = helper.getUserDao();
		Dao<SpaceObjectObservation, Integer> spaceObjectObservationDao = helper.getSpaceObjectObservationDao();
		
		User user = new User();
		user.setUsername("gprevost");
		user.setFirstname("Guillaume");
		user.setLastname("Prevost");
		user.setObservedSpaceObjectList(new ArrayList<SpaceObjectObservation>());
		userDao.create(user);
		
		SpaceObjectObservation obs = new SpaceObjectObservation(user, "My Observation", new Date(), 340, -1, 350, -1, 3, "Fireball", "Well Seen", "Amazing !");
		spaceObjectObservationDao.create(obs);
		SpaceObjectObservation obs2 = new SpaceObjectObservation(user, "My Observation 2", new Date(), 200, -1, 220, -1, 2, "Meteor", "Well Seen", "Great :)");
		spaceObjectObservationDao.create(obs);
		
		user.getObservedSpaceObjectList().add(obs);
		user.getObservedSpaceObjectList().add(obs2);
		userDao.create(user);
	}
	
	public static void LoadMeteorShowers (DatabaseHelper helper) throws SQLException {
		Dao<MeteorShowerEvent, Integer> meteorShowerEventDao = helper.getMeteorShowerEventDao();
		
		MeteorShowerEvent shower = new MeteorShowerEvent("Quadrantids", "The Quadrantids have the potential to be the strongest shower of the year but usually fall short due to the short length of maximum activity (6 hours) and the poor weather experienced during early January. The average hourly rates one can expect under dark skies is 25. These meteors usually lack persistent trains but often produce bright fireballs. Due to the high northerly declination (celestial latitude) these meteors are not well seen from the southern hemisphere.", 
				"2003 EH (Asteroid)", ShowerClass.CLASS_I, "01-01-2013", "11-01-2013", "02-01-2013 05:00:00", 42.2, "15:18", "49.5", 283.16, 20, 120);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Lyrids", "The Lyrids are a medium strength shower that usually produces good rates for three nights centered on the maximum. These meteors also usually lack persistent trains but can produce fireballs. These meteors are best seen from the northern hemisphere where the radiant is high in the sky at dawn. Activity from this shower can be seen from the southern hemisphere, but at a lower rate.", 
				"C/1861 G1 (Thatcher)", ShowerClass.CLASS_I, "16-04-2013", "26-04-2013", "21-04-2013 04:00:00", 48.4, "18:04", "34.0", 32.32, 12, 18);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Eta Aquariids", "The Eta Aquariids are a strong shower when viewed from the southern tropics. From the equator northward, they usually only produce medium rates of 10-30 per hour just before dawn. Activity is good for a week centered the night of maximum activity. These are swift meteors that produce a high percentage of persistent trains, but few fireballs. The Eta Aquariids are a strong shower when viewed from the southern tropics. From the equator northward, they usually only produce medium rates of 10-30 per hour just before dawn. Activity is good for a week centered the night of maximum activity. These are swift meteors that produce a high percentage of persistent trains, but few fireballs.", 
				"1P/Halley", ShowerClass.CLASS_I, "19-04-2013", "26-05-2013", "05-05-2013 04:00:00", 66.9, "22:32", "1.0", 046.8, 26, 60);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Delta Aquarids", "The Delta Aquariids are another strong shower best seen from the southern tropics. North of the equator the radiant is located lower in the southern sky and therefore rates are less than seen from further south. These meteors produce good rates for a week centered on the night of maximum. These are usually faint meteors that lack both persistent trains and fireballs.", 
				"96P/Machholz?", ShowerClass.CLASS_I, "21-07-2013", "23-08-2013", "28-07-2013 03:00:00", 42.0, "22:40", "16.4", 126.9, 22, 20);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Alpha Capricornids", "The Alpha Capricornids are active from July 11 through August with a \"plateau-like\" maximum centered on July 29. This shower is not very strong and rarely produces in excess of five shower members per hour. What is notable about this shower is the number of bright fireballs produced during its activity period. This shower is seen equally well on either side of the equator.", 
				"169P/NEAT", ShowerClass.CLASS_II, "11-07-2013", "10-08-2013", "29-07-2013 01:00:00", 24.0, "20:28", "10.2", 127.0, 22, 4);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Perseids", "The Perseids are the most popular meteor shower as they peak on warm August nights as seen from the northern hemisphere. The Perseids are active from July 13 to August 26. They reach a strong maximum on August 12 or 13, depending on the year. Normal rates seen from rural locations range from 50-75 shower members per hour at maximum.", 
				"109P/Swift-Tuttle", ShowerClass.CLASS_I, "13-07-2013", "26-08-2013", "12-08-2013 04:00:00", 60.0, "03:12", "57.6", 140.0, 6, 100);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Southern Taurids", "The Southern Taurids are a long-lasting shower that reaches a barely noticeable maximum on October 9 or 10. The shower is active for more than two months but rarely produces more than five shower members per hour, even at maximum activity. The Taurids (both branches) are rich in fireballs and are often responsible for increased number of fireball reports from September through November.", 
				"2P/Encke", ShowerClass.CLASS_II, "07-09-2013", "19-11-2013", "09-10-2013 02:00:00", 28.9, "02:08", "08.7", 197.0, 6, 5);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Northern Taurids", "This shower is much like the Southern Taurids, just active a bit later in the year. When the two showers are active simultaneously in late October and early November, there is sometimes an notable increase in the fireball activity. There seems to be a seven year periodicity with these fireballs. 2008 was the last remarkable year. Perhaps 2015 will be the next?", 
				"2P/Encke", ShowerClass.CLASS_II, "19-10-2013", "10-12-2013", "13-11-2013 00:00:00", 29.4, "03:54", "22.5", 229.0, 8, 5);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Leonids", "The Leonids are best known for producing great meteor storms in the years of 1833, 1866, 1966, and 2001. These outbursts of meteor activity are best seen when the parent object, comet 55P/Tempel-Tuttle, is near perihelion (closest approach to the sun).", 
				"55P/Tempel-Tuttle", ShowerClass.CLASS_I, "05-11-2013", "30-11-2013", "18-11-2013 05:00:00", 70.6, "10:08", "21.6", 236.1, 15, 15);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Geminids", "The Geminids are usually the strongest meteor shower of the year and meteor enthusiasts are certain to circle December 13 and 14 on their calendars. This is the one major shower that provides good activity prior to midnight as the constellation of Gemini is well placed from 10pm onward. The Geminids are often bright and intensely colored. Due to their medium-slow velocity, persistent trains are not usually seen. These meteors are also seen in the southern hemisphere, but only during the middle of the night and at a reduced rate.", 
				"3200 Phaethon (asteroid)", ShowerClass.CLASS_I, "04-12-2013", "16-12-2013", "13-12-2013 01:00:00", 35.0, "07:33", "32.2", 261.5, 11, 120);
		meteorShowerEventDao.create(shower);
		shower = new MeteorShowerEvent("Ursids", "The Ursids are often neglected due to the fact it peaks just before Christmas and the rates are much less than the Geminds, which peaks just a week before the Ursids. Observers will normally see 5-10 Ursids per hour during the late morning hours on the date of maximum activity. There have been occasional outbursts when rates have exceeded 25 per hour. These outbursts appear unrelated to the perihelion dates of comet 8P/Tuttle. This shower is strictly a northern hemisphere event as the radiant fails to clear the horizon or does so simultaneously with the start of morning twilight as seen from the southern tropics.", 
				"8P/Tuttle", ShowerClass.CLASS_I, "17-12-2013", "24-12-2013", "22-12-2013 05:00:00", 32.6, "14:28", "74.8", 270.7, 18, 10);
		meteorShowerEventDao.create(shower);
	}
}
