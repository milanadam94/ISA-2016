package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Restaurant;
import com.sms.dao.RestaurantDao;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantDao restaurantDao;
	
	@Override
	public List<Restaurant> loadRestaurants() {
		return restaurantDao.findAll();
	}

}
