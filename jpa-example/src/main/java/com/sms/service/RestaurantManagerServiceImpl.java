package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.ActiveUser;
import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.Menu;
import com.sms.beans.Offerer;
import com.sms.beans.Restaurant;
import com.sms.beans.RestaurantManager;
import com.sms.beans.SysUser;
import com.sms.beans.Tender;
import com.sms.beans.UserType;
import com.sms.dao.ActiveUserDao;
import com.sms.dao.DrinkDao;
import com.sms.dao.FoodDao;
import com.sms.dao.MenuDao;
import com.sms.dao.OffererDao;
import com.sms.dao.RestaurantDao;
import com.sms.dao.RestaurantManagerDao;
import com.sms.dao.TenderDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class RestaurantManagerServiceImpl implements RestaurantManagerService{

	@Autowired
	private RestaurantManagerDao restaurantManagerDao;

	@Autowired
	private ActiveUserDao activeUserDao;
	
	@Autowired
	private RestaurantDao restourantDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private DrinkDao drinkDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OffererDao offererDao;
	
	@Autowired
	private TenderDao tenderDao;
	
	@Override
	public Restaurant getRestaurant(String restManagerID) {
		// TODO Auto-generated method stub
		ActiveUser activeUser = activeUserDao.findByEmail(restManagerID);
		if(activeUser == null || !activeUser.getUserType().equals(UserType.RESTAURANTMANAGER)){
			return null;
		}
				
		RestaurantManager restManager = restaurantManagerDao.findByUserId(activeUser.getUser().getId());

		if(restManager != null){
			return restManager.getRestaurant();
		}
		
		return null;
		
	}

	@Override
	public RestaurantManager getRestaurantManager(String restManagerID) {
		// TODO Auto-generated method stub
		ActiveUser activeUser = activeUserDao.findByEmail(restManagerID);
		if(activeUser == null || !activeUser.getUserType().equals(UserType.RESTAURANTMANAGER)){
			return null;
		}
				
		return restaurantManagerDao.findByUserId(activeUser.getUser().getId());
	}

	@Override
	public Menu getMenu(String restManagerID, Restaurant restourant) {
		// TODO Auto-generated method stub

		ActiveUser activeUser = activeUserDao.findByEmail(restManagerID);
		if(activeUser == null || !activeUser.getUserType().equals(UserType.RESTAURANTMANAGER)){
			return null;
		}
		
		
		RestaurantManager manager = restaurantManagerDao.findByUserId(activeUser.getUser().getId());
		if(manager == null){
			return null;
		}
		
		if(manager.getRestaurant().getId() != restourant.getId()){
			return null;
		}
		
		return menuDao.findByRestaurant(restourant);
	}

	@Override
	public void deleteFood(Integer foodID, Integer menuID) {
		// TODO Auto-generated method stub
		
		Menu menu = menuDao.findById(menuID);
		if(menu == null){
			return;
		}
		
		
		for(Food food : menu.getFoods()){
			if(food.getId().equals(foodID)){
				menu.getFoods().remove(food);
				break;
			}
		}
		
		menuDao.save(menu);
		
		Food food = new Food();
		food.setId(foodID);
		foodDao.delete(food);
		
		
	}

	@Override
	public void addFood(Integer menuID, Food newFood) {
		// TODO Auto-generated method stub
		
		Menu menu = menuDao.findById(menuID);
		if(menu == null){
			return;
		}
		
		if(newFood.getName().equals("") || newFood.getPrice() < 0){
			return;
		}
		
		foodDao.save(newFood);
		
		menu.getFoods().add(newFood);		
		menuDao.save(menu);
	}

	@Override
	public void addDrink(Integer menuID, Drink newDrink) {
		// TODO Auto-generated method stub

		Menu menu = menuDao.findById(menuID);
		if(menu == null){
			return;
		}
		
		if(newDrink.getName().equals("") || newDrink.getPrice() < 0){
			return;
		}
		
		drinkDao.save(newDrink);
		
		menu.getDrinks().add(newDrink);		
		menuDao.save(menu);
	}

	@Override
	public void deleteDrink(Integer drinkID, Integer menuID) {
		// TODO Auto-generated method stub
		Menu menu = menuDao.findById(menuID);
		
		if(menu == null){
			return;
		}
		
		for(Drink drink : menu.getDrinks()){
			if(drink.getId().equals(drinkID)){
				menu.getDrinks().remove(drink);
				break;
			}
		}
		
		menuDao.save(menu);
		
		Drink drink = new Drink();
		drink.setId(drinkID);
		drinkDao.delete(drink);
	}

	@Override
	public void changeFood(Food newFood, Integer menuID) {
		// TODO Auto-generated method stub
		
		Menu menu = menuDao.findById(menuID);
		if(menu == null){
			return;
		}
		
		Food oldFood = foodDao.findById(newFood.getId());
		
		if(oldFood != null){
			foodDao.save(newFood);
		}
		
	}

	@Override
	public void changeDrink(Drink newDrink, Integer menuID) {
		// TODO Auto-generated method stub
		Menu menu = menuDao.findById(menuID);
		if(menu == null){
			return;
		}
		
		Drink oldDrink = drinkDao.findById(newDrink.getId());
		
		if(oldDrink != null){
			drinkDao.save(newDrink);
		}
	}

	@Override
	public String registarOfferer(SysUser user) {
		// TODO Auto-generated method stub
		
		if(user.getEmail() == null || user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword() == null || user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		else if(user.getName() == null || user.getName().equals(""))
			return Message.NAMEERROR;
		else if(user.getLastName() == null || user.getLastName().equals(""))
			return Message.LASTNAMEERROR;
		else if(user.getUserType() == null || user.getUserType().equals(""))
			return Message.REGISTRATIONERROR;
		
		SysUser sysUser = userDao.findByEmail(user.getEmail());
		
		if(sysUser != null)
			return Message.REGISTRATIONERROR;
	
		SysUser newUser = new SysUser(user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), user.getUserType());
		Offerer rest = new Offerer(newUser);
		userDao.save(newUser);
		offererDao.save(rest);
		
		return Message.ERRORFREE;
	}

	@Override
	public String createTender(Tender newTender, String userEmail) {
		// TODO Auto-generated method stub
		
		RestaurantManager restManager = getRestaurantManager(userEmail);
		
		if(restManager == null){
			return Message.USERNOTFOUNDERROR;
		}
		
		newTender.setRestaurant(restManager.getRestaurant());	
		tenderDao.save(newTender);
		
		return Message.ERRORFREE;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
