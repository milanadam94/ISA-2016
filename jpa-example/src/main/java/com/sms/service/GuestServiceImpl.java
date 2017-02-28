package com.sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.beans.Friendship;
import com.sms.beans.Guest;
import com.sms.beans.Invite;
import com.sms.beans.Reservation;
import com.sms.dao.FriendshipDao;
import com.sms.dao.GuestDao;
import com.sms.dao.InviteDao;
import com.sms.dao.ReservationDao;
import com.sms.dao.UserDao;
import com.sms.utilities.Message;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao guestDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendshipDao friendshipDao;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private InviteDao inviteDao;
	
	@Override
	public Guest getGuestByUserId(Integer userId) {

		Guest guest = guestDao.findByUserId(userId);
		
		List<Friendship> friendships = friendshipDao.findByGuest(guest.getId());
		List<Guest> friends = new ArrayList<Guest>();
		for(Friendship friendship : friendships) {
			Guest friend = guestDao.findById(friendship.getFriend());
			friends.add(friend);
		}
		guest.setFriends(friends);
		
		return guest;
		
		
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
	public String addFriend(Integer guestId, Integer friendId) {
		
		if(guestId == null || friendId == null)
			return Message.REQUESTERROR;
		
		Guest friend = guestDao.findById(friendId);
		Guest guest = guestDao.findById(guestId);
		
		if(friend == null || guestId == null)
			return Message.REQUESTERROR;
		
		friend.addFriendRequest(guest);
		guestDao.save(friend);
		
		return Message.ERRORFREE;
	}

	@Override
	public String removeFriend(Integer guestId, Integer friendId) {
		if(guestId == null || friendId == null)
			return Message.REQUESTERROR;
		
		List<Friendship> friendships = friendshipDao.findByGuest(guestId);
		
		for(Friendship f : friendships){
			friendshipDao.delete(f);
		}
		
		friendships = friendshipDao.findByGuest(friendId);
		
		for(Friendship f : friendships){
			friendshipDao.delete(f);
		}
		
		
		return Message.ERRORFREE;
	}

	@Override
	public String acceptRequest(Integer guestId, Integer friendId) {
		if(guestId == null || friendId == null)
			return Message.REQUESTERROR;
		
		Guest friend = guestDao.findById(friendId);
		Guest guest = guestDao.findById(guestId);
		
		if(guest == null || friend == null)
			return Message.REQUESTERROR;
		
		guest.removeFriendRequest(friend);
		guestDao.save(guest);
		
		Friendship friendship1 = new Friendship(guestId, friendId);
		Friendship friendship2 = new Friendship(friendId, guestId);
		
		friendshipDao.save(friendship1);
		friendshipDao.save(friendship2);
		
		return Message.ERRORFREE;
	}

	@Override
	public String declineRequest(Integer guestId, Integer friendId) {
		if(guestId == null || friendId == null)
			return Message.REQUESTERROR;
		
		Guest guest = guestDao.findById(guestId);
		Guest friend = guestDao.findById(friendId);
		
		if(guest == null)
			return Message.REQUESTERROR;
		
		guest.removeFriendRequest(friend);
		guestDao.save(guest);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Reservation> loadGuestReservations(Integer userId) {
		if(userId == null)
			return new ArrayList<Reservation>();
		Guest guest = guestDao.findByUserId(userId);
		
		return reservationDao.findByGuest(guest);
	}

	@Override
	public String cancelReservation(Reservation reservation) {
		if(reservation == null || reservation.getId() == null)
			return Message.REQUESTERROR;
		
		reservationDao.delete(reservation);
		
		return Message.ERRORFREE;
	}

	@Override
	public List<Invite> loadGuestInvites(Integer userId) {
		if(userId == null)
			return new ArrayList<Invite>();
		
		Guest guest = guestDao.findByUserId(userId);
		
		return inviteDao.findByGuest(guest);
	}

	@Override
	public String inviteFriend(Invite invite) {
		
		if(invite.getGuest() == null || invite.getGuest().getId() == null || invite.getFriend() == null || invite.getFriend().getId() == null
			|| invite.getReservation() == null || invite.getReservation().getId() == null)
			return Message.REQUESTERROR;
		
		inviteDao.save(invite);
		
		return Message.ERRORFREE;
		
	}

}
