package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.GuestTable;
import com.sms.beans.Restaurant;

public interface GuestTableDao extends Repository<GuestTable, Long> {
	
	public List<GuestTable> findByRestaurantId(Integer restaurantId);

	public void save(GuestTable table);
	
	public List<GuestTable> findAll();
	
	public void delete(GuestTable table);
	
//	public GuestTable findByRestaurantAndXCoordAndYCoord(Restaurant restaurant, Double xCoord, Double yCoord);
}
