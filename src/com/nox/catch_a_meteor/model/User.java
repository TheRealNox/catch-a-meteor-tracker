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

import java.util.Collection;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Representation of an application user
 * @author Guillaume Prevost
 * @since 20th Apr. 2013
 */
@DatabaseTable(tableName = "user")
public class User {

	@DatabaseField(generatedId = true)
	private Integer _id;
	
	@DatabaseField
	private String username;
	
	@DatabaseField(canBeNull = true, dataType = DataType.STRING)
	private String firstname;
	
	@DatabaseField(canBeNull = true, dataType = DataType.STRING)
	private String lastname;

	@ForeignCollectionField(eager = false)
    public Collection<SpaceObjectObservation> observedSpaceObjectList;
	
	public User() {
	}
	
	public User(String username, String firstname, String lastname, Collection<SpaceObjectObservation> observedSpaceObjectList) {
		setObservedSpaceObjectList(observedSpaceObjectList);
		setUsername(username);
		setFirstname(firstname);
		setLastname(lastname);
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Collection<SpaceObjectObservation> getObservedSpaceObjectList() {
		return this.observedSpaceObjectList;
	}

	public void setObservedSpaceObjectList(Collection<SpaceObjectObservation> observedSpaceObjectList) {
		this.observedSpaceObjectList = observedSpaceObjectList;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	
}
