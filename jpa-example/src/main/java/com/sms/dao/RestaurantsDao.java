package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Restaurant;

public interface RestaurantsDao extends Repository<Restaurant, Long>{

	public List<Restaurant> findAll();
}
