package com.sms.utilities;

import java.util.Date;

public class GuestsInDate {

	private Date guestDate;
	private int numberOfGuests;
	
	
	
	public GuestsInDate() {
		super();
	}
	public GuestsInDate(Date guestDate, int numberOfGuests) {
		super();
		this.setGuestDate(guestDate);
		this.numberOfGuests = numberOfGuests;
	}
	
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public Date getGuestDate() {
		return guestDate;
	}
	public void setGuestDate(Date guestDate) {
		this.guestDate = guestDate;
	}
	
	
	
	
}