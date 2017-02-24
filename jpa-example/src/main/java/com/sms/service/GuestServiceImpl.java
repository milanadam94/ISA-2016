package com.sms.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Guest;
import com.sms.dao.GuestDao;
import com.sms.utilities.Message;

@Service
public class GuestServiceImpl implements GuestService{
	
	@Autowired
	private GuestDao guestDao;
	
	@Override
	public Guest getGuestByUserId(Integer userId) {
		
		return guestDao.findByUserId(userId);
	}

	@Override
	public String editProfile(Guest guest) {
		
		if (guest.getUser().getEmail() == null || guest.getUser().getEmail().equals("")
				|| !Pattern.matches(
						"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
						guest.getUser().getEmail()))
			return Message.EMAILERROR;
		else if (guest.getUser().getPassword() == null || guest.getUser().getPassword().equals("")
				|| !Pattern.matches("[A-Za-z0-9]{7,}", guest.getUser().getPassword()))
			return Message.PASSWORDERROR;
		else if (guest.getUser().getName() == null || guest.getUser().getName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", guest.getUser().getName()))
			return Message.NAMEERROR;
		else if (guest.getUser().getLastName() == null || guest.getUser().getLastName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", guest.getUser().getLastName()))
			return Message.LASTNAMEERROR;
		else if (guest.getAddress() == null || guest.getAddress().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z0-9 ]+", guest.getAddress()))
			return Message.ADDRESSERROR;
		else if (guest.getCity() == null || guest.getCity().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", guest.getCity()))
			return Message.CITYERROR;
		
		guestDao.save(guest);
		
		return Message.ERRORFREE;
	}

}
