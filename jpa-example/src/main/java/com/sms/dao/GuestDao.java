package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;

public interface GuestDao extends Repository<Guest, Long> {

	public Guest save(Guest guest);
}
