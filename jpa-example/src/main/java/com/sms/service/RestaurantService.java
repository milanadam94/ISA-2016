package com.sms.service;

import java.util.List;

import com.sms.beans.Restaurant;

public interface RestaurantService {
	
	public List<Restaurant> loadRestaurants();
	
	public Restaurant getRestaurant(Integer restaurantID);
	
	public void saveChanges(Restaurant restaurant);
}
