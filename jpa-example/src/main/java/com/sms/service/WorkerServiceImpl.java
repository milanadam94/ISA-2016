package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;
import com.sms.beans.Drink;
import com.sms.beans.Food;
import com.sms.beans.GuestOrder;
import com.sms.beans.Menu;
import com.sms.beans.SysUser;
import com.sms.beans.Waiter;
import com.sms.dao.BartenderDao;
import com.sms.dao.CookDao;
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
	private MenuDao menuDao;
	
	@Autowired
	private GuestOrderDao guestOrderDao;
	
	private GuestOrder order = new GuestOrder();
	
	@Autowired
	private BartenderDao bartenderDao;
	
	@Autowired
	private CookDao cookDao;
	
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
		//System.out.println(drink.getQuantity()+" "+drink.getName());
		//System.out.println(drink.getPrepared());
		//drink.setPrepared(false);
		//order.getDrinks().add(drink);
	}

	@Override
	public void addOrderFood(Food food) {
		//System.out.println(food.getQuantity()+" "+food.getName());
		//System.out.println(food.getPrepared());
		//food.setPrepared(false);
		//order.getFoods().add(food);
	}

	@Override
	public void saveGuestOrder(GuestOrder order,Integer userId) {

		Waiter waiter = waiterDao.findByUserId(userId);
		order.setWaiter(waiter);
		order.setPrepared(false);
		guestOrderDao.save(order);
		//order = new GuestOrder();
	}

	@Override
	public List<GuestOrder> getGuestOrders(Integer userId) {
		Waiter waiter = waiterDao.findByUserId(userId);
		
		return guestOrderDao.findByWaiterId(waiter.getId());
	}

	@Override
	public void deleteGuestOrder(GuestOrder order) {
		guestOrderDao.delete(order);
		
	}

	@Override
	public int getTotal(Integer orderId) {
		GuestOrder order = guestOrderDao.findById(orderId);
		System.out.println(order.getId());
		int total = 0;
		/*for (Drink drink : order.getDrinks()) {
			System.out.println(drink.getQuantity()+" " +drink.getPrice());
			total += (drink.getQuantity() * drink.getPrice());
		}
		for(Food food : order.getFoods()){
			System.out.println(food.getQuantity()+" " +food.getPrice());
			total += (food.getQuantity() * food.getPrice());
		}*/
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

}
