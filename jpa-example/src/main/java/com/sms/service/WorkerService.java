package com.sms.service;

import com.sms.beans.Waiter;

public interface WorkerService {

	public Waiter getWaiter(Integer id);
	
	public void updateWaiterProfile(Waiter waiter);
}
