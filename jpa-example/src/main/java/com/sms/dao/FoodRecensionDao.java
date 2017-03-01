package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.FoodRecension;
import com.sms.beans.Restaurant;

public interface FoodRecensionDao extends Repository<FoodRecension, Long>{
	
	public List<FoodRecension> findByRestaurant(Restaurant restaurant);
}
