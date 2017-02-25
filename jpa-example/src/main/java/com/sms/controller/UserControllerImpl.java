package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.SysUser;
import com.sms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControllerImpl{

	@Autowired
	private UserService userService;
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SysUser login(@RequestBody SysUser user) {
		return userService.login(user);
	}
	
	@PostMapping(path = "/register", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String register(@RequestBody SysUser user) {
		return userService.register(user);
	}
	
	@PutMapping(path = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void logout(@RequestBody SysUser user) {
		userService.logout(user);
	}
	
	

}
