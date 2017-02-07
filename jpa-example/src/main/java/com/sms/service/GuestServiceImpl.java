package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Guest;
import com.sms.dao.GuestDao;

@Service
public class GuestServiceImpl implements GuestService {

	private GuestDao guestDao;
	
	@Autowired
	public GuestServiceImpl(GuestDao guestDao) {
		this.guestDao = guestDao;
	}
	
	public String validateLogin(Guest guest) {
		return null;
	}


}
