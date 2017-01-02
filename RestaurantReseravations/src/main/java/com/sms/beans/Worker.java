/***********************************************************************
 * Module:  Worker.java
 * Author:  Sasa
 * Purpose: Defines the Class Worker
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "worker")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Worker implements Serializable{
	
	private static final long serialVersionUID = 5512894695046769675L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "suite_size")
	private Integer suiteSize;
	
	@Column(name = "shoe_size")
	private Integer shoeSize;
	

	public Worker() {
	}

	public Worker(String name, String lastName, Integer suiteSize, Integer shoeSize) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.suiteSize = suiteSize;
		this.shoeSize = shoeSize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSuiteSize() {
		return suiteSize;
	}

	public void setSuiteSize(Integer suiteSize) {
		this.suiteSize = suiteSize;
	}

	public Integer getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Integer shoeSize) {
		this.shoeSize = shoeSize;
	}
	
	

}