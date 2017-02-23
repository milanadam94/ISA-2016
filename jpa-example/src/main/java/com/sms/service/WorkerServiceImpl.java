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
	
	//@Autowired
	//private ActiveUserDao activeUserDao;
	
	public Waiter getWaiter(String email) {
		SysUser newUser = userDao.findByEmail(email);
		System.out.println(newUser.getPassword());//ovde mi izbaci null
		Waiter waiter = waiterDao.findByUser(newUser);
		System.out.println(waiter.getBirthday());
		Waiter newWaiter = new Waiter(newUser.getEmail(), newUser.getPassword(), waiter.getSuitSize(), waiter.getShoeSize(), 
				waiter.getBirthday(), newUser);
		return newWaiter;
	}

}
