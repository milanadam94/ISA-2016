package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.GuestTable;
import com.sms.beans.Reservation;
import com.sms.beans.Restaurant;
import com.sms.dao.GuestTableDao;
import com.sms.dao.ReservationDao;
import com.sms.dao.RestaurantDao;
import com.sms.utilities.Message;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantDao restaurantDao;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private GuestTableDao guestTableDao;
	
	@Override
	public List<Restaurant> loadRestaurants() {
		return restaurantDao.findAll();
	}

	@Override
	public Restaurant getRestaurant(Integer restaurantID) {
		return restaurantDao.findById(restaurantID);
	}

	@Override
	public void saveChanges(Restaurant restaurant) {
		restaurantDao.save(restaurant);
	}

	@Override
	public List<Reservation> loadRestaurantReservations(Integer restaurantId) {
		return reservationDao.findByRestaurantId(restaurantId);
	}

	@Override
	public List<GuestTable> loadRestaurantTables(Integer restaurantId) {
		return guestTableDao.findByRestaurantId(restaurantId);
	}

	@Override
	public String reserveTables(Reservation reservation) {
		
		System.out.println(reservation.getRestaurant());
		System.out.println(reservation.getDuration());
		System.out.println(reservation.getReservationDateTime());
		System.out.println(reservation.getRestaurant());
		System.out.println(reservation.getGuest());
		System.out.println(reservation.getTables());
		if(reservation.getRestaurant() == null || reservation.getDuration() == null || reservation.getReservationDateTime() == null 
				|| reservation.getGuest() == null || reservation.getTables() == null || reservation.getTables().isEmpty())
			return Message.REQUESTERROR;
		
		reservationDao.save(reservation);
		
		return Message.ERRORFREE;
	}

}
