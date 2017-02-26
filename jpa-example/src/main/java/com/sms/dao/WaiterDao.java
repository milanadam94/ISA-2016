package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Waiter;

public interface WaiterDao extends Repository<Waiter, Long>{
	
	public Waiter save(Waiter waiter);
	
	public void delete(Waiter waiter);
	
	public Waiter findByUserId(Integer userId);
	
}
