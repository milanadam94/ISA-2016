package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.SysUser;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public String validateLogin(SysUser user) {
		if(user.getEmail().equals(""))
			return Message.EMAILERROR;
		else if(user.getPassword().equals(""))
			return Message.PASSWORDERROR;
		
		SysUser userList = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(userList == null)
			return Message.USERNOTFOUNDERROR;
		
		return Message.LOGINCOMPLETED;
	}


}
