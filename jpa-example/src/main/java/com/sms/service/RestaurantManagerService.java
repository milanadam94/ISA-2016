package com.sms.service;

import java.util.List;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.Menu;
import com.sms.beans.Offerings;
import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;
import com.sms.beans.Segment;
import com.sms.beans.SysUser;
import com.sms.beans.Tender;
import com.sms.beans.Waiter;

public interface RestaurantManagerService {

	public Restaurant getRestaurant(String restManagerID);
	
	public RestaurantManager getRestaurantManager(String restManagerID);
	
	public Menu getMenu(String restManagerID, Restaurant restourant);
	
	public void deleteFood(Integer foodID, Integer menuID);
	
	public void addFood(Integer menuID, Food newFood);
	
	public void addDrink(Integer menuID, Drink newDrink);
	
	public void deleteDrink(Integer drinkID, Integer menuID);
	
	public void changeFood(Food newFood, Integer menuID);
	
	public void changeDrink(Drink newDrink, Integer menuID);
	
	public String registarWorker(SysUser user, String managerID);
	
	public String registarOfferer(SysUser user);
	
	public String createTender(Tender newTender, String userEmail);

	public List<Tender> getAllMyTenders(String managerEmail);

	public List<Offerings> getOfferingsForTender(Integer tenderID);

	public String chooseOffering(Integer offeringID);

	public String addSegment(Segment newSegment, Integer restoranID);

	public List<Cook> getCooks(String managerEmail);

	public List<Bartender> getBartenders(String managerEmail);

	public List<Waiter> getWaiters(String managerEmail);
}
