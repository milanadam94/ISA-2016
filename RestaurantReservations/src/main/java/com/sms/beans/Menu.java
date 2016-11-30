/***********************************************************************
 * Module:  Menu.java
 * Author:  Sasa
 * Purpose: Defines the Class Menu
 ***********************************************************************/
package com.sms.beans;
import java.util.ArrayList;

public class Menu {
	
    private ArrayList<Food> foods;
    private ArrayList<Drink> drinks;
	public Menu() {
		super();
	}
	public Menu(ArrayList<Food> foods, ArrayList<Drink> drinks) {
		super();
		this.foods = foods;
		this.drinks = drinks;
	}
	public ArrayList<Food> getFoods() {
		return foods;
	}
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	public ArrayList<Drink> getDrinks() {
		return drinks;
	}
	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}
  
	

}