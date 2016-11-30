/***********************************************************************
 * Module:  Worker.java
 * Author:  Sasa
 * Purpose: Defines the Class Worker
 ***********************************************************************/
package com.sms.beans;

import java.util.Date;

public abstract class Worker {

	private String name;
	private String lastname;
	private Date birthdate;
	private int suiteSize;
	private int shoeSize;

	public Worker() {
		super();
	}

	public Worker(String name, String lastname, Date birthdate, int suiteSize, int shoeSize) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.suiteSize = suiteSize;
		this.shoeSize = shoeSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public int getSuiteSize() {
		return suiteSize;
	}

	public void setSuiteSize(int suiteSize) {
		this.suiteSize = suiteSize;
	}

	public int getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(int shoeSize) {
		this.shoeSize = shoeSize;
	}

}