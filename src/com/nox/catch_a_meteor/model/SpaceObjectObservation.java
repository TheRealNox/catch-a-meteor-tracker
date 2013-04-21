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

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nox.catch_a_meteor.units.GeocentricCoordinates;

/**
 * Representation of meteor observation
 * @author Guillaume Prevost
 * @since 20th Apr. 2013
 */
@DatabaseTable(tableName = "space_object_observation")
public class SpaceObjectObservation {

	@DatabaseField(generatedId = true)
	private Integer id;
	
	 @DatabaseField(canBeNull = false, foreign = true)
	 private User user;
	
	/**
	 * Title of the observation
	 */
	@DatabaseField
	private String title;
	
	/**
	 * Date and time of the observation
	 */
	@DatabaseField(dataType = DataType.DATE_STRING)
	private Date dateObserved;
	
	/**
	 * Coordinates of the space object in the sky map
	 */
	@DatabaseField
	private float ra;
	
	@DatabaseField
	private float dec;
	
	@DatabaseField
	private float raEnd;
	
	@DatabaseField
	private float decEnd;
		
	/**
	 * The magnitude of the observed meteor
	 */
	@DatabaseField
	private Integer magnitude;
	
	/**
	 * A meteor's type is either shower or sporadic:
	 * - Use the code of the meteor shower if it's part of a shower.
	 * - Use "SP" for sporadic.
	 */
	@DatabaseField
	private String type;

	/**
	 * The reliability of the observation (Well seen, Poorly seen, Other)
	 */
	@DatabaseField
	private String reliability;

	/**
	 * Further user comment about the observation
	 */
	@DatabaseField
	private String comment;


	public SpaceObjectObservation() {
	}
	
	public SpaceObjectObservation(User user, String title, Date dateObserved, float ra, float dec, float raEnd, float decEnd, Integer magnitude, String type, String reliability, String comment) {
		setUser(user);
		setTitle(title);
		setDateObserved(dateObserved);
		setRa(ra);
		setDec(dec);
		setRaEnd(raEnd);
		setDecEnd(decEnd);
		setMagnitude(magnitude);
		setType(type);
		setReliability(reliability);
		setComment(comment);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateObserved() {
		return dateObserved;
	}

	public void setDateObserved(Date dateObserved) {
		this.dateObserved = dateObserved;
	}

	public float getRa() {
		return ra;
	}

	public void setRa(float ra) {
		this.ra = ra;
	}

	public float getDec() {
		return dec;
	}

	public void setDec(float dec) {
		this.dec = dec;
	}
	
	public GeocentricCoordinates getCoordinates() {
		return GeocentricCoordinates.getInstance(ra, dec);
	}
	
	public float getRaEnd() {
		return raEnd;
	}

	public void setRaEnd(float raEnd) {
		this.raEnd = raEnd;
	}

	public float getDecEnd() {
		return decEnd;
	}

	public void setDecEnd(float decEnd) {
		this.decEnd = decEnd;
	}

	public GeocentricCoordinates getCoordinatesEnd() {
		return GeocentricCoordinates.getInstance(raEnd, decEnd);
	}
	
	public Integer getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(Integer magnitude) {
		this.magnitude = magnitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReliability() {
		return reliability;
	}

	public void setReliability(String reliability) {
		this.reliability = reliability;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
