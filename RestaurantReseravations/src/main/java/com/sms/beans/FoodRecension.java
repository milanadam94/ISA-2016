/***********************************************************************
 * Module:  FoodRecension.java
 * Author:  Sasa
 * Purpose: Defines the Class FoodRecension
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_recension")
public class FoodRecension implements Serializable {
	
	private static final long serialVersionUID = -6420718485111801149L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "grade")
	private Integer grade;
	
	public FoodRecension() {
	}
	
	public FoodRecension(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getGrade() {
		return grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}