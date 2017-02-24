package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;
import com.sms.service.RestaurantManagerService;
import com.sms.service.RestaurantService;

@Controller
@RequestMapping("/restManager")
public class RestaurantManagerController {
	
	@Autowired
	private RestaurantManagerService restManagerService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@GetMapping(path = "/myRestaurant/{restManagerID}")
	@ResponseBody
	public Restaurant getRestaurant(@PathVariable(value="restManagerID") String restManagerID) {
		return restManagerService.getRestaurant(restManagerID);
	}
	
	@PutMapping(path = "/saveRestaurantInfo/{restManagerID}", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String saveRestaurantInfo(Restaurant restaurant, @PathVariable(value="restManagerID") String restManagerID){
		
		if(restaurant.getName().equals("") || restaurant.getDescription().equals("")){
			return "Ne moze biti prazno!";
		}
		RestaurantManager manager = restManagerService.getRestaurantManager(restManagerID);
		Restaurant notModifiedRestaurant = restaurantService.getRestaurant(restaurant.getId());
		
		if(manager == null){
			return "Korisnik nije pronadjen!";
		}
		
		if(notModifiedRestaurant == null){
			return "Restoran nije pronadjen!";
		}
		
		if(manager.getRestaurant().getId() != restaurant.getId()){
			return "Nemate pravo pristupa ovom restoranu!";
		}
		
		if(notModifiedRestaurant.getName().trim().equals(restaurant.getName())
				&& notModifiedRestaurant.getDescription().trim().equals(restaurant.getDescription())
				&& notModifiedRestaurant.getCategory().equals(restaurant.getCategory())){
			return "Nema promena u unosu!";
		}
		
		restaurantService.saveChanges(restaurant);
		
		return "Uspesno promenjeni podaci!";
	}
	
	
}
