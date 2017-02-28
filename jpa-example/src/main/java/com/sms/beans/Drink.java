/***********************************************************************
 * Module:  Drink.java
 * Author:  Sasa
 * Purpose: Defines the Class Drink
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {

	private static final long serialVersionUID = 4275943328698609662L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Integer price;

	@Column(name = "grade")
	private Integer grade;

	public Drink() {
	}

	public Drink(String name, String description, Integer price, Integer grade) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = grade;
	}

	public Drink(String name, String description, Integer price, Integer grade, Integer quantity, Boolean prepared) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = grade;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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