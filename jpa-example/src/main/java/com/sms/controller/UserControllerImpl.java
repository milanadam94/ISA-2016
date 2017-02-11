package com.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuestControllerImpl{

	
	@GetMapping("/validateLogin")
	@ResponseBody
	public String validateLogin() {
		
		return "aa";
		//return guestService.validateLogin(guest);
	}
	
	

}
