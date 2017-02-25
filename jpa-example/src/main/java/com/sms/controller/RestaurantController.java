package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Restaurant;
import com.sms.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping(path = "/loadRestaurants", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Restaurant> loadRestaurants() {
		return restaurantService.loadRestaurants();
	}

}
