package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;

public interface GuestDao extends Repository<Guest, Integer> {

	public Guest save(Guest guest);
	
	public Guest findByUserId(Integer user);
	
	public Guest findById(Integer id);
}
