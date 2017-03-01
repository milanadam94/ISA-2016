package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.GuestOrder;
import com.sms.beans.GuestTable;
import com.sms.beans.Menu;
import com.sms.beans.Reservation;
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

	@PostMapping(path = "/loadRestaurantReservations/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Reservation> loadRestaurantReservations( @PathVariable(value="restaurantId")  Integer restaurantId) {
		return restaurantService.loadRestaurantReservations(restaurantId);
	}
	
	@PostMapping(path = "/getRestaurantMenu", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Menu getRestaurantMenu(@RequestBody Restaurant restaurant) {
		return restaurantService.getRestaurantMenu(restaurant);
	}
	
	@PostMapping(path = "/reserveTables", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String reserveTables(@RequestBody Reservation reservation) {
		return restaurantService.reserveTables(reservation);
	}
	
	@PostMapping(path = "/order", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String order(@RequestBody GuestOrder order) {
		return restaurantService.order(order);
	}
	
	@PostMapping(path = "/loadRestaurantTables/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<GuestTable> loadRestaurantTables( @PathVariable(value="restaurantId")  Integer restaurantId) {
		return restaurantService.loadRestaurantTables(restaurantId);
	}
}
