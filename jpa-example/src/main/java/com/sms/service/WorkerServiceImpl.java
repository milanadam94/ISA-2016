package com.sms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.DrinkOrder;
import com.sms.beans.Food;
import com.sms.beans.FoodOrder;
import com.sms.beans.GuestOrder;
import com.sms.beans.Menu;
import com.sms.beans.Restaurant;
import com.sms.beans.Schedule;
import com.sms.beans.SysUser;
import com.sms.beans.Waiter;
import com.sms.dao.BartenderDao;
import com.sms.dao.CookDao;
import com.sms.dao.DrinkOrderDao;
import com.sms.dao.FoodOrderDao;
import com.sms.dao.GuestOrderDao;
import com.sms.dao.MenuDao;
import com.sms.dao.UserDao;
import com.sms.dao.WaiterDao;
import com.sms.utilities.Message;

@Service
public class WorkerServiceImpl implements WorkerService	{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WaiterDao waiterDao;
	
	@Autowired
	private BartenderDao bartenderDao;
	
	@Autowired
	private CookDao cookDao;
	
	@Autowired
	private FoodOrderDao foodOrderDao;
	
	@Autowired
	private DrinkOrderDao drinkOrderDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private GuestOrderDao guestOrderDao;
	
	private GuestOrder order = new GuestOrder();
	
	public Waiter getWaiterByUserId(Integer userId) {
		
		SysUser newUser = userDao.findById(userId);
		Waiter waiter = waiterDao.findByUserId(newUser.getId());
		
		return waiter;
	}

	@Override
	public String  updateWaiterProfile(Waiter waiter) {

		waiterDao.save(waiter);
		userDao.save(waiter.getUser());
		
		return Message.ERRORFREE;
	}
	@Override
	public String saveFirstLogin(Waiter waiter) {
		SysUser user = waiter.getUser();
		user.setFirstLogin(true);
		userDao.save(user);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Food> getFoods(Integer userId) {
		Waiter waiter = waiterDao.findByUserId(userId);
		Menu menu = menuDao.findByRestaurant(waiter.getRestaurant());
		return menu.getFoods();
	}

	@Override
	public List<Drink> getDrinks(Integer userId) {
		Waiter waiter = waiterDao.findByUserId(userId);
		Menu menu = menuDao.findByRestaurant(waiter.getRestaurant());
		return menu.getDrinks();
	}

	@Override
	public void addOrderDrink(Drink drink) {
		System.out.println(drink.getQuantity()+" "+drink.getName());
		DrinkOrder drinkOrder = new DrinkOrder(drink, drink.getQuantity(), false);
		drinkOrderDao.save(drinkOrder);
		order.getDrinkOrders().add(drinkOrder);
		//guestOrderDao.save(order);
	}

	@Override
	public void addOrderFood(Food food) {
		System.out.println(food.getQuantity()+" "+food.getName());
		FoodOrder foodOrder = new FoodOrder(food, food.getQuantity(), false, false);
		foodOrderDao.save(foodOrder);
		order.getFoodOrders().add(foodOrder);
		guestOrderDao.save(order);
	}

	@Override
	public void saveGuestOrder(Integer userId) {

		Waiter waiter = waiterDao.findByUserId(userId);
		order.setWaiter(waiter);
		order.setPrepared(false);
		order.setRestaurant(waiter.getRestaurant());
		guestOrderDao.save(order);
		order = new GuestOrder();
	}

	@Override
	public List<GuestOrder> getGuestOrders(Integer userId) {
		Waiter waiter = waiterDao.findByUserId(userId);
		
		return guestOrderDao.findByWaiterId(waiter.getId());
	}

	@Override
	public void deleteGuestOrder(Integer id) {
		GuestOrder order = guestOrderDao.findById(id);
		guestOrderDao.delete(order);
		
	}

	@Override
	public int getTotal(Integer orderId) {
		GuestOrder order = guestOrderDao.findById(orderId);
		System.out.println(order.getId());
		int total = 0;
		for(DrinkOrder drink : order.getDrinkOrders()){
			total += drink.getQuantity() * (drink.getDrink().getPrice());
		}
		for(FoodOrder food : order.getFoodOrders()){
			total += food.getQuantity() * (food.getFood().getPrice());
		}
		return total;
	}

	@Override
	public Bartender getBartenderByUserId(Integer userId) {
		SysUser user = userDao.findById(userId);
		Bartender bartender = bartenderDao.findByUserId(user.getId());
		return bartender;
	}

	@Override
	public String updateBartenderProfile(Bartender bartender) {
		bartenderDao.save(bartender);
		userDao.save(bartender.getUser());
		
		return Message.ERRORFREE;
	}
	
	@Override
	public String saveFirstLogin(Bartender bartender) {
		SysUser user = bartender.getUser();
		user.setFirstLogin(true);
		bartenderDao.save(bartender);
		userDao.save(user);
		
		return Message.ERRORFREE;
	}	
	
	@Override
	public List<DrinkOrder> getDrinkOrders(Integer userId) {
		Bartender bartender = bartenderDao.findByUserId(userId);
		Restaurant restaurant = bartender.getRestaurant();
		List<GuestOrder> orders = guestOrderDao.findByRestaurant(restaurant);
		List<DrinkOrder> drinks = new ArrayList<DrinkOrder>();
		for(GuestOrder order : orders){
			for(DrinkOrder drink : order.getDrinkOrders()){
				if(!drink.getPrepared()){
					drinks.add(drink);
				}
			}
		}
		return drinks;
	}
	
	@Override
	public void setDrinkOrderPrepared(DrinkOrder drinkOrder) {
		List<DrinkOrder> drinks = drinkOrderDao.findAll();
		for(DrinkOrder drink : drinks){
			if(drink.getId().equals(drinkOrder.getId())){
				drink.setPrepared(true);
				drinkOrderDao.save(drink);
				break;
			}
			
		}
		List<GuestOrder> orders = guestOrderDao.findAll();
		for(GuestOrder order : orders){
			List<DrinkOrder> drinkOrders = order.getDrinkOrders();
			for(DrinkOrder drinkk : drinkOrders){
				if(drinkk.getId().equals(drinkOrder.getId())){
					if(order.getDrinkOrdersPrepared() && order.getDrinkOrdersPrepared()){
						order.setPrepared(true);
						guestOrderDao.save(order);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public Cook getCookByUserId(Integer userId) {
		SysUser user = userDao.findById(userId);
		Cook cook = cookDao.findByUserId(user.getId());
		return cook;
	}

	@Override
	public String updateCookProfile(Cook cook) {
		cookDao.save(cook);
		userDao.save(cook.getUser());
		
		return Message.ERRORFREE;
	}
	
	@Override
	public String saveFirstLogin(Cook cook) {
		SysUser user = cook.getUser();
		user.setFirstLogin(true);
		cookDao.save(cook);
		userDao.save(user);
		
		return Message.ERRORFREE;
	}

	@Override
	public Boolean getFirstLogin(Integer userId) {
		SysUser user = userDao.findById(userId);
		System.out.println(user.getEmail() +" "+ user.getFirstLogin());
		return user.getFirstLogin();
	}
	
	@Override
	public List<FoodOrder> getFoodOrders(Integer userId) {
		Cook cook = cookDao.findByUserId(userId);
		Restaurant restaurant = cook.getRestaurant();
		List<GuestOrder> orders = guestOrderDao.findByRestaurant(restaurant);
		List<FoodOrder> foods = new ArrayList<FoodOrder>();
		for(GuestOrder order : orders){
			for(FoodOrder food : order.getFoodOrders()){
				//if( (!food.getPrepared()) && (food.getFood().getFoodType().equals(cook.getCookType())) && (!food.getStarted())){
				if( (!food.getPrepared()) && (food.getFood().getFoodType().equals(cook.getCookType()))){
					foods.add(food);
				}
			}
		}
		return foods;	
	}

	@Override
	public void setStartPrepareFood(FoodOrder foodOrder) {
		List<FoodOrder> orders = foodOrderDao.findAll();
		for(FoodOrder order : orders){
			if(order.getId().equals(foodOrder.getId())){
				order.setStarted(true);
				foodOrderDao.save(order);
				break;
			}
		}
	}

	@Override
	public void setPrepareFoodDone(FoodOrder foodOrder) {
		List<FoodOrder> orders = foodOrderDao.findAll();
		for(FoodOrder order : orders){
			if(order.getId().equals(foodOrder.getId())){
				order.setPrepared(true);
				foodOrderDao.save(order);
				break;
			}
		}
		List<GuestOrder> guestOrders = guestOrderDao.findAll();
		for(GuestOrder order : guestOrders){
			List<FoodOrder> foodOrders = order.getFoodOrders();
			for(FoodOrder foodd : foodOrders){
				if(foodd.getId().equals(foodOrder.getId())){
					if(order.getDrinkOrdersPrepared() && order.getDrinkOrdersPrepared()){
						order.setPrepared(true);
						guestOrderDao.save(order);
						break;
					}
				}
			}
		}
	}

	@Override
	public List<Waiter> getWaiters(Integer userId) {
		Waiter waiter = waiterDao.findByUserId(userId);
		List<Waiter> waiters = waiterDao.findByRestaurant(waiter.getRestaurant());
		
		return waiters;
	}

	@Override
	public List<Cook> getCooks(Integer userId) {
		Cook cook = cookDao.findByUserId(userId);
		List<Cook> cooks = cookDao.findByRestaurant(cook.getRestaurant());
		
		return cooks;
	}

	@Override
	public List<Bartender> getBartenders(Integer userId) {
		Bartender bartender = bartenderDao.findByUserId(userId);
		List<Bartender> bartenders = bartenderDao.findByRestaurant(bartender.getRestaurant());
		
		return bartenders;
	}

}
