package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.SysUser;
import com.sms.beans.Waiter;
import com.sms.dao.UserDao;
import com.sms.dao.WaiterDao;

@Service
public class WorkerServiceImpl implements WorkerService	{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WaiterDao waiterDao;
	
	public Waiter getWaiter(Integer id) {
		SysUser newUser = userDao.findById(id);
		Waiter waiter = waiterDao.findByUserId(newUser.getId());
		
		return waiter;
	}

	@Override
	public void updateWaiterProfile(Waiter waiter) {
		waiterDao.delete(waiter);
		waiterDao.save(waiter);
	}

}
