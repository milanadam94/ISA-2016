package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.GuestOrder;
import com.sms.beans.Menu;
import com.sms.beans.SysUser;
import com.sms.beans.Waiter;
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
	private MenuDao menuDao;
	
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
		order.getDrinks().add(drink);
	}

	@Override
	public void addOrderFood(Food food) {
		System.out.println(food.getQuantity()+" "+food.getName());
		order.getFoods().add(food);
	}

	
}
