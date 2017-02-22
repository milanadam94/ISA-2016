package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sms.service.GuestService;

@Controller
public class GuestController {
	
	@Autowired
	private GuestService guestService;
}
