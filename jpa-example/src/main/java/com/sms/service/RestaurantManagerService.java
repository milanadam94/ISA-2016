package com.sms.service;

import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;

public interface RestaurantManagerService {

	public Restaurant getRestaurant(String restManagerID);
	
	public RestaurantManager getRestaurantManager(String restManagerID);
}
