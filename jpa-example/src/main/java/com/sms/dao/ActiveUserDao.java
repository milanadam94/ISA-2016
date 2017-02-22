package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.ActiveUser;

public interface ActiveUserDao extends Repository<ActiveUser, Long> {
	
	public ActiveUser save(ActiveUser activeUser);
	
	public ActiveUser findByEmail(String email);

}
