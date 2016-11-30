/***********************************************************************
 * Module:  Offerer.java
 * Author:  Sasa
 * Purpose: Defines the Class Offerer
 ***********************************************************************/
package com.sms.beans;
import java.util.ArrayList;

public class Offerer {
	
   private String email;
   private String name;
   private String password;
   private String surname;
   
   public ArrayList<Offerings> offerings;
   public ArrayList<Supply> supplies;
	public Offerer() {
		super();
	}
	public Offerer(String email, String name, String password, String surname) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
   
	
}