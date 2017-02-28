package com.sms.service;

import java.util.List;

import com.sms.beans.GuestTable;
import com.sms.beans.Reservation;
import com.sms.beans.Restaurant;

public interface RestaurantService {
	
	public List<Restaurant> loadRestaurants();
	
	public Restaurant getRestaurant(Integer restaurantID);
	
	public void saveChanges(Restaurant restaurant);

	public List<Reservation> loadRestaurantReservations(Integer restaurantId);

	public List<GuestTable> loadRestaurantTables(Integer restaurantId);

	public String reserveTables(Reservation reservation);
}
