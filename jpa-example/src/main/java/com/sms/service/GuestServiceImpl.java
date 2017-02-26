package com.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Guest;
import com.sms.dao.GuestDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao guestDao;

	@Autowired
	private UserDao userDao;
	
	@Override
	public Guest getGuestByUserId(Integer userId) {

		return guestDao.findByUserId(userId);
	}

	@Override
	public String editProfile(Guest guest) {

		if(guest.getUser().getEmail() == null || guest.getUser().getEmail() == "" 
				|| guest.getUser().getId() == null || guest.getId() == null) {
			return Message.CREDIDENTIALSERROR;
		}
		else if (guest.getUser().getPassword() == null || guest.getUser().getPassword().equals("")
				|| !Pattern.matches("[A-Za-z0-9]{7,}", guest.getUser().getPassword()))
			return Message.PASSWORDERROR;
		else if (guest.getUser().getName() == null || guest.getUser().getName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", guest.getUser().getName()))
			return Message.NAMEERROR;
		else if (guest.getUser().getLastName() == null || guest.getUser().getLastName().equals("")
				|| !Pattern.matches("[A-Z][A-Za-z ]+", guest.getUser().getLastName()))
			return Message.LASTNAMEERROR;
		else if (guest.getAddress() == null || !Pattern.matches("[A-Z][A-Za-z0-9 ]*", guest.getAddress()))
			return Message.ADDRESSERROR;
		else if (guest.getCity() == null || !Pattern.matches("[A-Z][A-Za-z ]*", guest.getCity()))
			return Message.CITYERROR;
		
		guestDao.save(guest);
		userDao.save(guest.getUser());

		return Message.ERRORFREE;
	}

	@Override
	public List<Guest> searchGuests(Integer guestId, String searchInput) {
		if(searchInput == null || searchInput == "" || guestId == null)
			return new ArrayList<Guest>();
		
		searchInput = searchInput.replaceAll("\\s+","").toLowerCase();
		
		return guestDao.searchGuests(guestId, searchInput);
	}

	@Override
	public String addFriend(Integer guestId, Guest friend) {
		
		if(guestId == null || friend == null || friend.getId() == null)
			return Message.REQUESTERROR;
		
		return null;
		
	}

}
