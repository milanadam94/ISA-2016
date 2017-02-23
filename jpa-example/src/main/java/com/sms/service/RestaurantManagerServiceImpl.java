package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;
import com.sms.dao.RestaurantManagerDao;

@Service
public class RestaurantManagerServiceImpl implements RestaurantManagerService{

	@Autowired
	private RestaurantManagerDao restaurantManagerDao;

	@Override
	public Restaurant getRestaurant(String restManagerID) {
		// TODO Auto-generated method stub
		
		RestaurantManager restManager = restaurantManagerDao.findByEmail(restManagerID);
		if(restManager != null){
			return restManager.getRestaurant();
		}
		return null;
		
	}

	@Override
	public RestaurantManager getRestaurantManager(String restManagerID) {
		// TODO Auto-generated method stub
		return restaurantManagerDao.findByEmail(restManagerID);
	}

	
	
}
