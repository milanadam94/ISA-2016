/***********************************************************************
 * Module:  Food.java
 * Author:  Sasa
 * Purpose: Defines the Class Food
 ***********************************************************************/
package com.sms.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food implements Serializable{
	
	private static final long serialVersionUID = -2727346146111461837L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private Integer price;

	@Column(name = "grade")
	private Integer grade;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FoodRecension> foodRecensions;

	public Food() {
	}

	public Food(String name, String description, Integer price, Integer grade, List<FoodRecension> foodRecensions) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = grade;
		this.foodRecensions = foodRecensions;
	}

	public Food(String name, String Description, Integer price){
		this.name = name;
		this.description = description;
		this.price = price;
		this.grade = 0;
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

	public List<FoodRecension> getFoodRecensions() {
		return foodRecensions;
	}

	public void setFoodRecensions(List<FoodRecension> foodRecensions) {
		this.foodRecensions = foodRecensions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	
}