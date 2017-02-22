package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;
import com.sms.beans.UserType;
import com.sms.dao.RestaurantsDao;
import com.sms.dao.SystemManagerDao;
import com.sms.utilities.Message;

@Service
public class SystemManagerServiceImpl implements SystemManagerService{

	@Autowired
	private SystemManagerDao sysManagerDao;
	
	@Autowired
	private RestaurantsDao restDao;
	
	public String registarRestManager(SysUser user) {
		if(user.getEmail() == null || user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword() == null || user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		else if(user.getName() == null || user.getName().equals(""))
			return Message.NAMEERROR;
		else if(user.getLastName() == null || user.getLastName().equals(""))
			return Message.LASTNAMEERROR;
		
		SysUser sysUser = sysManagerDao.findByEmail(user.getEmail());
		
		if(sysUser != null)
			return Message.REGISTRATIONERROR;
		
		SysUser newUser = new SysUser(user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), UserType.RESTAURANTMANAGER);
		//RestaurantManager rest = new RestaurantManager(newUser);
		sysManagerDao.save(newUser);
		
		return Message.ERRORFREE;
	}

	@Override
	public String registarRestaurant(Restaurant restaurant) {
		
		if(restaurant.getName() == null || restaurant.getName().equals(""))
			return Message.NAMEERROR;
		else if(restaurant.getDescription() == null || restaurant.getDescription().equals(""))
			return Message.DESCRIPTIONERROR;
		else if(restaurant.getCategory() == null || restaurant.getCategory().equals(""))
			return Message.CATEGORYERROR;
		
		Restaurant rest = sysManagerDao.findByName(restaurant.getName());
		
		if(rest != null)
			return Message.REGISTRATIONERROR;
		
		Restaurant newRest = new Restaurant(restaurant.getName(), restaurant.getDescription(), restaurant.getCategory());
		
		sysManagerDao.save(newRest);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return restDao.findAll();
	}

	@Override
	public List<SysUser> getRestManagers() {
		return sysManagerDao.findByUserType(UserType.RESTAURANTMANAGER);
	}
	

}
