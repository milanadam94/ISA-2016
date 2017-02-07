package com.sms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;

public interface GuestDao extends Repository<Guest, Long>{
	
	public Page<Guest> findAll(Pageable pageable);

	

}