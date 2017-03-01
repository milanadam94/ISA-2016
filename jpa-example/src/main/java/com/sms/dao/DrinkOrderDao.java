package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.DrinkOrder;

public interface DrinkOrderDao extends Repository<DrinkOrder, Long>{
	
	public DrinkOrder save(DrinkOrder drinkOrder);
	
	public DrinkOrder delete(DrinkOrder drinkOrder);
	
	public List<DrinkOrder> findByPrepared(Boolean prepared);
	
	public List<DrinkOrder> findAll();
}
