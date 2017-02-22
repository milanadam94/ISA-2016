package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.dao.GuestDao;

@Service
public class GuestServiceImpl implements GuestService{
	
	@Autowired
	private GuestDao guestDao;
}
