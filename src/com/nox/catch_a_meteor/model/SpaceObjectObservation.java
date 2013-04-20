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
	 * Coordinates of where the space object started in the sky map
	 */
	@DatabaseField
	private int startPoint;
	
	/**
	 * Coordinates of where the space object ended in the sky map
	 */
	@DatabaseField
	private int endPoint;
	
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
	@DatabaseField(dataType = DataType.ENUM_STRING)
	private ReliabilityType reliability;

	/**
	 * Further user comment about the observation
	 */
	@DatabaseField
	private String comment;


	public SpaceObjectObservation() {
	}
	
	public SpaceObjectObservation(User user, String title, Date dateObserved, int startPoint, int endPoint, Integer magnitude, String type, ReliabilityType reliability, String comment) {
		setUser(user);
		setTitle(title);
		setDateObserved(dateObserved);
		setStartPoint(startPoint);
		setEndPoint(endPoint);
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

	public int getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	public int getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
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

	public ReliabilityType getReliability() {
		return reliability;
	}

	public void setReliability(ReliabilityType reliability) {
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


	/**
	 * Levels of reliability of a space object observation
	 * @author Guillaume Prevost
	 * @since 20th Apr. 2013
	 */
	private enum ReliabilityType {
		WELL_SEEN,
		POORLY_SEEN,
		OTHER
	}
}
