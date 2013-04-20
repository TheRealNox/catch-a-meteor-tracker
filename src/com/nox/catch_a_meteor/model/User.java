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

public class User extends Storable {

	private String username;
	private String firstname;
	private String lastname;

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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
