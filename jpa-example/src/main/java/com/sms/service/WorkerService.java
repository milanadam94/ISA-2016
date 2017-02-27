package com.sms.service;

import com.sms.beans.Waiter;

public interface WorkerService {

	public Waiter getWaiterByUserId(Integer userId);
	
	public String updateWaiterProfile(Waiter waiter);
}
