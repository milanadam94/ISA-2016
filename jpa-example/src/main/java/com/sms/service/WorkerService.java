package com.sms.service;

import java.util.List;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.DrinkOrder;
import com.sms.beans.Food;
import com.sms.beans.FoodOrder;
import com.sms.beans.GuestOrder;
import com.sms.beans.Schedule;
import com.sms.beans.SysUser;
import com.sms.beans.Waiter;

public interface WorkerService {

	public Waiter getWaiterByUserId(Integer userId);
	
	public String updateWaiterProfile(Waiter waiter);
	
	public String saveFirstLogin(Waiter waiter);
	
	public List<Food> getFoods(Integer userId);
	
	public List<Drink> getDrinks(Integer userId);
	
	public void addOrderDrink(Drink drink);
	
	public void addOrderFood(Food food);
	
	public void saveGuestOrder(Integer userId);
	
	public List<GuestOrder> getGuestOrders(Integer userId);
	
	public void deleteGuestOrder(Integer id);
	
	public int getTotal(Integer orderId);
	
	public List<Waiter> getWaiters(Integer userId);
	
	//sankeri
	public Bartender getBartenderByUserId(Integer userId);
	
	public String updateBartenderProfile(Bartender bartender);
	
	public String saveFirstLogin(Bartender bartender);
	
	public List<DrinkOrder> getDrinkOrders(Integer userId);
	
	public void setDrinkOrderPrepared(DrinkOrder drinkOrder);
	
	public List<Bartender> getBartenders(Integer userId);
	
	//kuvari
	public Cook getCookByUserId(Integer userId);
	
	public String updateCookProfile(Cook cook);
	
	public String saveFirstLogin(Cook cook);
	
	public List<FoodOrder> getFoodOrders(Integer userId);
	
	public void setStartPrepareFood(FoodOrder foodOrder);
	
	public void setPrepareFoodDone(FoodOrder foodOrder);
	
	public List<Cook> getCooks(Integer userId);
	
	//svim radnicima
	public Boolean getFirstLogin(Integer userId);
	
}
