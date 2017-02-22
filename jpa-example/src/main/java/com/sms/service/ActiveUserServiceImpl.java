package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.dao.ActiveUserDao;

@Service
public class ActiveUserServiceImpl implements ActiveUserService {
	
	@Autowired
	private ActiveUserDao activeUserDao;

}
