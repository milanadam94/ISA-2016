package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.FoodOrder;

public interface FoodOrderDao extends Repository<FoodOrder, Long>{
	
	public FoodOrder save(FoodOrder foodOrder);
	
	public FoodOrder delete(FoodOrder foodOrder);
	
	public List<FoodOrder> findAll();
}
