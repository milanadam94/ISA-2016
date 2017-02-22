package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sms.service.ActiveUserService;

@Controller
public class ActiveUserController {
	
	@Autowired
	private ActiveUserService activeUserService;

}
