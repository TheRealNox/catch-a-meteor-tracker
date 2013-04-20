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
// file: Storable.java
// created: 20 Apr 2013
//


package com.nox.catch_a_meteor.model;

public abstract class Storable {
	
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
