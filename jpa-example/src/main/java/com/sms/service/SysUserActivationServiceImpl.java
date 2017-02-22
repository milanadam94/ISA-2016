package com.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Guest;
import com.sms.beans.SysUser;
import com.sms.beans.SysUserActivation;
import com.sms.beans.UserType;
import com.sms.dao.GuestDao;
import com.sms.dao.SysUserActivationDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class SysUserActivationServiceImpl implements SysUserActivationService {

	@Autowired
	private SysUserActivationDao sysUserActivationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GuestDao guestDao;
	
	@Override
	public String activate(String activationCode) {

		SysUserActivation sysUserActivation = sysUserActivationDao.findByActivationCode(activationCode);

		if (sysUserActivation == null)
			return Message.ACTIVATIONERROR;

		SysUser sysUser = new SysUser(sysUserActivation.getEmail(), sysUserActivation.getPassword(),
				sysUserActivation.getName(), sysUserActivation.getLastName(), sysUserActivation.getUserType());

		sysUser = userDao.save(sysUser);

		if (sysUser.getUserType().equals(UserType.GUEST)) {
			Guest guest = new Guest(sysUser);
			guestDao.save(guest);
		}

		return Message.ERRORFREE;
	}

}
