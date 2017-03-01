package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;
import com.sms.beans.Reservation;

public interface ReservationDao extends Repository<Reservation, Long>{
	
	public List<Reservation> findByRestaurantId(Integer restaurantId);
	
	public Reservation save(Reservation reservation);

	public List<Reservation> findByGuest(Guest guest);

	public void delete(Reservation reservation);
	
	public Reservation findById(Integer reservationId);

}
