package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.SysUser;
import com.sms.beans.UserType;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public String validateLogin(SysUser user) {
		if(user.getEmail() == null || user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword() == null || user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		
		SysUser sysUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(sysUser == null)
			return Message.USERNOTFOUNDERROR;
		
		return Message.ERRORFREE;
	}

	@Override
	public String validateRegistration(SysUser user) {
		
		if(user.getEmail() == null || user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword() == null || user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		else if(user.getName() == null || user.getName().equals(""))
			return Message.NAMEERROR;
		else if(user.getLastName() == null || user.getLastName().equals(""))
			return Message.PASSWORDERROR;
		
		SysUser sysUser = userDao.findByEmail(user.getEmail());
		
		if(sysUser != null)
			return Message.REGISTRATIONERROR;
		
		SysUser newUser = new SysUser(user.getEmail(), user.getPassword(), user.getName(), user.getLastName(), UserType.GUEST);
		
		userDao.save(newUser);
		
		return Message.ERRORFREE;
	}


}
