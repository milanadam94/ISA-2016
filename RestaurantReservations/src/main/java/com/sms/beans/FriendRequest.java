/***********************************************************************
 * Module:  FriendRequest.java
 * Author:  Sasa
 * Purpose: Defines the Class FriendRequest
 ***********************************************************************/
package com.sms.beans;

public class FriendRequest {
	
	private Guest senderGuest;
	
	public FriendRequest() {
		super();
	}

	public FriendRequest(Guest senderGuest) {
		super();
		this.senderGuest = senderGuest;
	}

	public Guest getSenderGuest() {
		return senderGuest;
	}

	public void setSenderGuest(Guest senderGuest) {
		this.senderGuest = senderGuest;
	}
	

}