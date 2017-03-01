package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantRecension;

public interface RestaurantRecensionDao extends Repository<RestaurantRecension, Long> {
	
	public List<RestaurantRecension> findByRestaurant(Restaurant restaurant);
	
}

