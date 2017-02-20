package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.SysUser;
import com.sms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControllerImpl{

	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	@ResponseBody
	public String login(SysUser user) {
		
		return service.validateLogin(user);
	}
	
	@PostMapping("/register")
	@ResponseBody
	public String register(SysUser user) {
		
		return service.validateRegistration(user);
	}
	
	

}
