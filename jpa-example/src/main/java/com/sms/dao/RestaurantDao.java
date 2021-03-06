package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Restaurant;

public interface RestaurantDao extends Repository<Restaurant, Long> {
	
	public List<Restaurant> findAll();
	
	public Restaurant findById(Integer id);
	
	public void save(Restaurant restaurant);
}
