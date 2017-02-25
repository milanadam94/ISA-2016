package com.sms.service;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.sms.beans.ActiveUser;
import com.sms.beans.Guest;
import com.sms.beans.SysUser;
import com.sms.beans.SysUserActivation;
import com.sms.dao.ActiveUserDao;
import com.sms.dao.GuestDao;
import com.sms.dao.SysUserActivationDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ActiveUserDao activeUserDao;

	@Autowired
	private SysUserActivationDao sysUserActivationDao;
	
	@Autowired
	private GuestDao guestDao;

	@Autowired
	private MailSender mailSender;

	public SysUser login(SysUser user) {

		SysUser sysUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (sysUser == null)
			return new SysUser();

		ActiveUser activeUser = activeUserDao.findByEmail(sysUser.getEmail());

		if (activeUser != null)
			return new SysUser();
		
		Guest guest = guestDao.findByUserId(sysUser.getId());
		guest.setVisits(guest.getVisits() + 1);
		guestDao.save(guest);

		activeUser = new ActiveUser(sysUser, sysUser.getEmail(), sysUser.getUserType());

		activeUserDao.save(activeUser);

		return sysUser;
	}

	@Override
	public String register(SysUser user) {

		if (user.getEmail() == null || user.getEmail().equals("")
				|| !Pattern.matches(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
						user.getEmail()))
			return Message.EMAILERROR;
		else if (user.getPassword() == null || user.getPassword().equals("")
				|| !Pattern.matches("[A-Za-z0-9]{7,}", user.getPassword()))
			return Message.PASSWORDERROR;
		else if (user.getName() == null || user.getName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", user.getName()))
			return Message.NAMEERROR;
		else if (user.getLastName() == null || user.getLastName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", user.getLastName()))
			return Message.LASTNAMEERROR;

		SysUser sysUser = userDao.findByEmail(user.getEmail());

		if (sysUser != null)
			return Message.REGISTRATIONERROR;
		
		SysUserActivation sysUserActivation = sysUserActivationDao.findByEmail(user.getEmail());
		
		if(sysUserActivation != null)
			sysUserActivationDao.delete(sysUserActivation);

		sysUserActivation = new SysUserActivation(user.getEmail(), user.getPassword(), user.getName(),
				user.getLastName(), user.getUserType());
		
		sysUserActivation.setActivationCode(UUID.randomUUID().toString());
		
		sysUserActivationDao.save(sysUserActivation);
		
		sendEmail(user.getEmail(), sysUserActivation.getActivationCode());

		return Message.ERRORFREE;
	}

	private void sendEmail(String email, String activationCode) {
		
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Potvrda registracije");
		message.setFrom("isasms2016@gmail.com");
		message.setTo(email);
		message.setText("http://localhost:8080/activation/activate" + "/" + activationCode);

		try {
			mailSender.send(message);
		} catch (MailException e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public void logout(SysUser user) {
		
		if(user.getId() != null) {
			
			ActiveUser activeUser = activeUserDao.findByUser(user);
			
			if(activeUser != null)
			{
				activeUserDao.delete(activeUser);
			}
			
		}
		
	}
	
}
