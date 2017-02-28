package com.sms.service;

import java.util.List;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.GuestOrder;
import com.sms.beans.Waiter;

public interface WorkerService {

	public Waiter getWaiterByUserId(Integer userId);
	
	public String updateWaiterProfile(Waiter waiter);
	
	public String saveFirstLogin(Waiter waiter);
	
	public List<Food> getFoods(Integer userId);
	
	public List<Drink> getDrinks(Integer userId);
	
	public void addOrderDrink(Drink drink);
	
	public void addOrderFood(Food food);
	
	public void saveGuestOrder(GuestOrder order,Integer userId);
	
	public List<GuestOrder> getGuestOrders(Integer userId);
	
	public void deleteGuestOrder(GuestOrder order);
	
	public int getTotal(Integer orderId);
	
	//sankeri
	public Bartender getBartenderByUserId(Integer userId);
	
	public String updateBartenderProfile(Bartender bartender);
	
	public String saveFirstLogin(Bartender bartender);
	
	//kuvari
	public Cook getCookByUserId(Integer userId);
	
	public String updateCookProfile(Cook cook);
	
	public String saveFirstLogin(Cook cook);
	
	//svim radnicima
	public Boolean getFirstLogin(Integer userId);
}
