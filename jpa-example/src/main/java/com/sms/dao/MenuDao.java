package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Menu;
import com.sms.beans.Restaurant;

public interface MenuDao extends Repository<Menu, Long>{

	public Menu findByRestaurant(Restaurant restaurant);
	public Menu findById(Integer id);
	public void save(Menu menu);
}
