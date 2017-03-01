package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.GuestOrder;
import com.sms.beans.Restaurant;

public interface GuestOrderDao extends Repository<GuestOrder, Long>{
	
	public GuestOrder save(GuestOrder guestOrder);
	
	public GuestOrder delete(GuestOrder guestOrder);
	
	public List<GuestOrder> findByWaiterId(Integer userId);
	
	public List<GuestOrder> findByRestaurant(Restaurant restaurant);
	
	public List<GuestOrder> findAll();
	
	public GuestOrder findById(Integer orderId);
}
