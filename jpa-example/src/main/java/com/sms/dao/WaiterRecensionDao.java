package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Waiter;
import com.sms.beans.WaiterRecension;

public interface WaiterRecensionDao extends Repository<WaiterRecension, Long>{

	public List<WaiterRecension> findByWaiter(Waiter waiter);
	
	
}