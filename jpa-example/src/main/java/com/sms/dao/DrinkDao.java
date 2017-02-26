package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Drink;

public interface DrinkDao extends Repository<Drink, Long>{
	
	public void delete(Drink drink);
	
	public void save(Drink drink);
	
	public Drink findById(Integer id);
}
