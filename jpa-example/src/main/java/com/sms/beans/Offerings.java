/***********************************************************************
 * Module:  Offerings.java
 * Author:  Sasa
 * Purpose: Defines the Class Offerings
 ***********************************************************************/
package com.sms.beans;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="offerings")
public class Offerings  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6111365528723775068L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String description;
	
   	private Date deliveryDate;
   	
   	private Boolean isAccepted;
   	
   	private Integer price;
   	
   	@ManyToOne
   	private Offerer offerer;
   	
   	@ManyToOne
   	private Tender tender;
   	
	public Offerings() {
		super();
	}
	public Offerings(String description, Date deliveryDate, Boolean isAccepted) {
		super();
		this.description = description;
		this.deliveryDate = deliveryDate;
		this.isAccepted = isAccepted;
	}
	
	public Offerings(String description, Integer price, Date deliveryDate){
		this.description = description;
		this.price = price;
		this.deliveryDate = deliveryDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	public Offerer getOfferer() {
		return offerer;
	}
	public void setOfferer(Offerer offerer) {
		this.offerer = offerer;
	}
	public Tender getTender() {
		return tender;
	}
	public void setTender(Tender tender) {
		this.tender = tender;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	
}