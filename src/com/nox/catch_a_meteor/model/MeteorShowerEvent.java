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
// file: User.java
// created: 20 Apr 2013
//


package com.nox.catch_a_meteor.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Representation of a meteor shower event
 * @author Guillaume Prevost
 * @since 20th Apr. 2013
 */
@DatabaseTable(tableName = "meteor_shower_event")
public class MeteorShowerEvent {

	@DatabaseField(generatedId = true)
	private Integer _id;
	
	/**
	 * Name of the event. Named for the constellation or closest star within a constellation where
	 * the radiant is located at maximum activity
	 */
	@DatabaseField
	private String name;
	
	/**
	 * Description of the event
	 */
	@DatabaseField
	private String description;
	
	/**
	 * Parent object of the event
	 */
	@DatabaseField
	private String parentObject;
	
	/**
	 * Class: A scale developed by Robert Lunsford to group meteor showers by their intensity
	 */
	@DatabaseField(dataType = DataType.ENUM_STRING)
	private ShowerClass showerClass;
	
	/**
	 * Date of the start of the event
	 */
	@DatabaseField(dataType = DataType.DATE_STRING)
	private Date startDate;
	
	/**
	 * Date of the end of the event
	 */
	@DatabaseField(dataType = DataType.DATE_STRING)
	private Date endDate;
	
	/**
	 * Date of the end of the best observation time
	 */
	@DatabaseField(dataType = DataType.DATE_STRING)
	private Date peakDate;

	/**
	 * The velocity of the event
	 */
	@DatabaseField
	private Double velocity;
	
	/**
	 * Radiant Right Ascension: the area in the sky where shower meteors seem to appear from. This position
	 * is given in right ascension (celestial longitude) and declination (celestial latitude).
	 * The radiant must be near or above the horizon in order to witness activity from a particular
	 * shower.
	 */
	@DatabaseField
	private String radiantRightAscension;
	
	/**
	 * Radiant Declinaison: the area in the sky where shower meteors seem to appear from. This position
	 * is given in right ascension (celestial longitude) and declination (celestial latitude).
	 * The radiant must be near or above the horizon in order to witness activity from a particular
	 * shower.
	 */
	@DatabaseField
	private String radiantDeclinaison;
	
	/**
	 * The equivalent solar longitude of the date of maximum activity.
	 * Solar longitude is measured in degrees (0-359) with 0 occurring at the exact moment 
	 * of the spring equinox, 90 at the summer solstice, 180 at the autumnal equinox, and 
	 * 270 at the winter solstice. Scientists use this time measurement as it is independent 
	 * of the calendar.
	 */
	@DatabaseField
	private Double solarLongitude;
	
	/**
	 * Moon: the age of the moon in days where 0 is new, 7 is first quarter, 14 is full,
	 * and 21 is last quarter. Meteor activity is best seen in the absence of moonlight so
	 * showers reaching maximum activity when the moon is less than 10 days old or more than 25
	 * are much more favorably observed than those situated closer to the full moon.
	 */
	@DatabaseField
	private Integer moon;
	
	/**
	 * Zenith Hourly Rate, the average maximum number of shower meteors visible 
	 * per hour if the radiant is located exactly overhead and the limiting magnitude equals +6.5 (a very dark sky). 
	 * Actual counts rarely reach this figure as the zenith angle of the radiant is usually less and 
	 * the limiting magnitude is usually lower than +6.5. ZHR is a useful tool when comparing the actual observed 
	 * rates between individual observers as it sets observing conditions for all to the same standards.
	 */
	@DatabaseField
	private Integer zenithHourlyRate;

	public MeteorShowerEvent() {
	}
	
	public MeteorShowerEvent(String name, String description, String parentObject, ShowerClass showerClass, 
			Date startDate, Date endDate, Date peakDate, Double velocity, 
			String radiantRightAscension, String RadiantDeclinaison, Double solarLongitude, Integer moon, Integer zenithHourlyRate) {
		setName(name);
		setDescription(description);
		setParentObject(parentObject);
		setShowerClass(showerClass);
		setStartDate(startDate);
		setEndDate(endDate);
		setPeakDate(peakDate);
		setRadiantRightAscension(radiantRightAscension);
		setRadiantDeclinaison(RadiantDeclinaison);
		setSolarLongitude(solarLongitude);
		setMoon(moon);
		setZenithHourlyRate(zenithHourlyRate);
	}
	
	public MeteorShowerEvent(String name, String description, String parentObject, ShowerClass showerClass, 
			String startDateStr, String endDateStr, String peakDateStr, Double velocity, 
			String radiantRightAscension, String RadiantDeclinaison, Double solarLongitude, Integer moon, Integer zenithHourlyRate) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date startDate;
		try { startDate = dateFormatter.parse(startDateStr); } 
		catch (ParseException e) { startDate = new Date(); }
		Date endDate;
		try { endDate = dateFormatter.parse(endDateStr); } 
		catch (ParseException e) { endDate = new Date(); }
		Date peakDate;
		try { peakDate = dateTimeFormatter.parse(peakDateStr); } 
		catch (ParseException e) { peakDate = new Date(); }
		
		setName(name);
		setDescription(description);
		setParentObject(parentObject);
		setShowerClass(showerClass);
		setStartDate(startDate);
		setEndDate(endDate);
		setPeakDate(peakDate);
		setRadiantRightAscension(radiantRightAscension);
		setRadiantDeclinaison(RadiantDeclinaison);
		setSolarLongitude(solarLongitude);
		setMoon(moon);
		setZenithHourlyRate(zenithHourlyRate);
	}

	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentObject() {
		return parentObject;
	}

	public void setParentObject(String parentObject) {
		this.parentObject = parentObject;
	}

	public ShowerClass getShowerClass() {
		return showerClass;
	}

	public void setShowerClass(ShowerClass showerClass) {
		this.showerClass = showerClass;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getPeakDate() {
		return peakDate;
	}

	public void setPeakDate(Date peakDate) {
		this.peakDate = peakDate;
	}

	public Double getVelocity() {
		return velocity;
	}

	public void setVelocity(Double velocity) {
		this.velocity = velocity;
	}
	
	public String getRadiantRightAscension() {
		return radiantRightAscension;
	}
	

	public void setRadiantRightAscension(String radiantRightAscension) {
		this.radiantRightAscension = radiantRightAscension;
	}
	

	public String getRadiantDeclinaison() {
		return radiantDeclinaison;
	}
	

	public void setRadiantDeclinaison(String radiantDeclinaison) {
		this.radiantDeclinaison = radiantDeclinaison;
	}
	

	public Double getSolarLongitude() {
		return solarLongitude;
	}
	

	public void setSolarLongitude(Double solarLongitude) {
		this.solarLongitude = solarLongitude;
	}
	

	public Integer getMoon() {
		return moon;
	}
	

	public void setMoon(Integer moon) {
		this.moon = moon;
	}
	

	public Integer getZenithHourlyRate() {
		return zenithHourlyRate;
	}
	

	public void setZenithHourlyRate(Integer zenithHourlyRate) {
		this.zenithHourlyRate = zenithHourlyRate;
	}



	/**
	 * Class: A scale developed by Robert Lunsford to group meteor showers by their intensity:
		Class I: the strongest annual showers with ZHR’s normally ten or better.
		Class II: reliable minor showers with ZHR’s normally two to ten.
		Class III: showers that do not provide annual activity. These showers are rarely active
		yet have the potential to produce a major display on occasion.
		Class IV: weak minor showers with ZHR’s rarely exceeding two. The study of these
		showers is best left to experienced observers who use plotting and angular velocity
		estimates to determine shower association. Observers with less experience are urged to
		limit their shower associations to showers with a rating of I to III. These showers
		are also good targets for video and photographic work
	 * @author Guillaume Prevost
	 * @since 20th Apr. 2013
	 */
	public enum ShowerClass {
		CLASS_I,
		CLASS_II,
		CLASS_III,
		CLASS_IV
	}
}
