package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;
import com.sms.service.SystemManagerService;

@Controller
@RequestMapping("/sysManager")
public class SystemManagerContollerImpl {

	@Autowired
	private SystemManagerService service;
	
	@PostMapping(path = "/registerRestManager", produces = "text/plain")
	@ResponseBody
	public String registerRestManager(SysUser user) {
		return service.registarRestManager(user);
	}
	
	@PostMapping(path = "/registerRestaurant", produces = "text/plain")
	@ResponseBody
	public String registerRestaurant(Restaurant restaurant){
		return service.registarRestaurant(restaurant);
	}
	
	@GetMapping(path = "/restaurants", produces = "application/json")
	@ResponseBody
	public List<Restaurant> getRests(){
		return service.getRestaurants();
	}
	
	@GetMapping(path = "/restManagers", produces = "application/json")
	@ResponseBody
	public List<SysUser> getRestManagers(){
		return service.getRestManagers();
	}
}
