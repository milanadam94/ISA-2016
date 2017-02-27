package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.SysUser;
import com.sms.beans.Waiter;
import com.sms.dao.UserDao;
import com.sms.dao.WaiterDao;
import com.sms.utilities.Message;

@Service
public class WorkerServiceImpl implements WorkerService	{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WaiterDao waiterDao;
	
	public Waiter getWaiterByUserId(Integer userId) {
		
		SysUser newUser = userDao.findById(userId);
		Waiter waiter = waiterDao.findByUserId(newUser.getId());
		
		return waiter;
	}

	@Override
	public String  updateWaiterProfile(Waiter waiter) {

		waiterDao.save(waiter);
		userDao.save(waiter.getUser());
		
		return Message.ERRORFREE;
	}

}
