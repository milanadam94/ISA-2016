package com.sms.service;

import java.util.List;

import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.Waiter;

public interface WorkerService {

	public Waiter getWaiterByUserId(Integer userId);
	
	public String updateWaiterProfile(Waiter waiter);
	
	public List<Food> getFoods(Integer userId);
	
	public List<Drink> getDrinks(Integer userId);
	
	public void addOrderDrink(Drink drink);
	
	public void addOrderFood(Food food);
	
	public void saveGuestOrder(Integer userId);
}
