package com.sms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public List<Reservation> loadRestaurantReservations(Integer restaurantId) {
		return reservationDao.findByRestaurantId(restaurantId);
	}

	@Override
	public List<GuestTable> loadRestaurantTables(Integer restaurantId) {
		return guestTableDao.findByRestaurantId(restaurantId);
	}

	@Override
	@Transactional
	public String reserveTables(Reservation reservation) {

		if (reservation == null || reservation.getRestaurant() == null
				|| reservation.getRestaurant().getId() == null || reservation.getDuration() == null
				|| reservation.getReservationDateTime() == null || reservation.getGuest() == null
				|| reservation.getTables() == null || reservation.getTables().isEmpty())
			return Message.RESERVATIONFAILED;

		
			List<Reservation> reservations = reservationDao.findByRestaurantId(reservation.getRestaurant().getId());
			List<Integer> tableIds = new ArrayList<Integer>();
			for (Reservation res : reservations) {
				tableIds.clear();
				for (GuestTable table : res.getTables()) {
					tableIds.add(table.getId());
				}
				for (GuestTable table : reservation.getTables()) {
					if (tableIds.contains(table.getId())) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat timeSDF = new SimpleDateFormat("hh:mm:ss");
						Date oldResDate = null;
						Date newResDate = null;
						try {
							oldResDate = simpleDateFormat.parse(res.getReservationDateTime().split("T")[0]);
							newResDate = simpleDateFormat.parse(reservation.getReservationDateTime().split("T")[0]);
							
							if(!oldResDate.before(newResDate) && !oldResDate.after(newResDate))
							{
								Date oldTimeDate = timeSDF.parse(res.getReservationDateTime().split("T")[1].substring(0, 8));
								Date newTimeDate = timeSDF.parse(reservation.getReservationDateTime().split("T")[1].substring(0, 8));
								
								Calendar oldTimePlusDuration = Calendar.getInstance(); 
								oldTimePlusDuration.setTime(oldTimeDate); 
								oldTimePlusDuration.add(Calendar.HOUR_OF_DAY, res.getDuration()); 
								oldTimePlusDuration.getTime(); 
								
								Calendar newTimePlusDuration = Calendar.getInstance(); 
								newTimePlusDuration.setTime(newTimeDate); 
								newTimePlusDuration.add(Calendar.HOUR_OF_DAY, reservation.getDuration()); 
								newTimePlusDuration.getTime(); 
								
								if(!newTimeDate.after(oldTimePlusDuration.getTime()) && !newTimePlusDuration.getTime().before(oldTimeDate))
								{
									return Message.RESERVATIONFAILED;
								}
							}
						} catch (ParseException ex) {
							System.out.println("Exception " + ex);
						}
						break;
					}
				}
			}
		

		reservationDao.save(reservation);

		return Message.ERRORFREE;
	}

	@Override
	public Menu getRestaurantMenu(Restaurant restaurant) {
		if (restaurant == null || restaurant.getId() == null) {
			return new Menu();
		}

		return menuDao.findByRestaurant(restaurant);
	}

	@Override
	public String order(GuestOrder order) {

		if (order == null || order.getDrinkOrders() == null || order.getFoodOrders() == null
				|| order.restaurant == null)
			return Message.REQUESTERROR;

		guestOrderDao.save(order);

		return Message.ERRORFREE;
	}

}
