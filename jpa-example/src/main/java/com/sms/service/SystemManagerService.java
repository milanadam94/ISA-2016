package com.sms.service;

import java.util.List;

import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;

public interface SystemManagerService {

	public String registarRestManager(SysUser user, Integer restoranID);
	
	public String registarRestaurant(Restaurant restaurant);
	
	public List<Restaurant> getRestaurants();
	
	public List<SysUser> getRestManagers();
}
