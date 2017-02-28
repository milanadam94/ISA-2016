package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.GuestOrder;
import com.sms.beans.GuestTable;
import com.sms.beans.Menu;
import com.sms.beans.Reservation;
import com.sms.beans.Restaurant;
import com.sms.dao.GuestOrderDao;
import com.sms.dao.GuestTableDao;
import com.sms.dao.MenuDao;
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
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private GuestOrderDao guestOrderDao;
	
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
		
		if(reservation.getRestaurant() == null || reservation.getDuration() == null || reservation.getReservationDateTime() == null 
				|| reservation.getGuest() == null || reservation.getTables() == null || reservation.getTables().isEmpty())
			return Message.REQUESTERROR;
		
		reservationDao.save(reservation);
		
		return Message.ERRORFREE;
	}

	@Override
	public Menu getRestaurantMenu(Restaurant restaurant) {
		if(restaurant == null || restaurant.getId() == null)
		{
			return new Menu();
		}
		
		return menuDao.findByRestaurant(restaurant);
	}

	@Override
	public String order(GuestOrder order) {
		
		if(order == null || order.getDrinkOrders() == null || order.getFoodOrders() == null || order.restaurant == null)
			return Message.REQUESTERROR;
		
		guestOrderDao.save(order);
		
		
		return Message.ERRORFREE;
	}

}
