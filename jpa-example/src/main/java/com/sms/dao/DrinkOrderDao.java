package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.DrinkOrder;

public interface DrinkOrderDao extends Repository<DrinkOrder, Long>{
	
	public DrinkOrder save(DrinkOrder drinkOrder);
	
	public DrinkOrder delete(DrinkOrder drinkOrder);
}
