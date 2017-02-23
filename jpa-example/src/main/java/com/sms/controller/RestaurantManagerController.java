package com.sms.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;
import com.sms.service.RestaurantManagerService;

@Controller
@RequestMapping("/restManager")
public class RestaurantManagerController {
	
	@Autowired
	private RestaurantManagerService restManagerService;
	
	@GetMapping(path = "/myRestaurant/{restManagerID}")
	@ResponseBody
	public Restaurant getRestaurant(@PathVariable(value="restManagerID") String restManagerID) {
		return restManagerService.getRestaurant(restManagerID);
	}
	
	
}
