package com.sms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.sms.beans.Friendship;
import com.sms.beans.Guest;
import com.sms.beans.History;
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
	
	@Autowired
	private MailSender mailSender;
	
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
		
		List<Reservation> reservations = reservationDao.findByGuest(guest);
		List<Reservation> reservationsToReturn = new ArrayList<Reservation>();
		Date now = new Date();
		for(Reservation reservation : reservations) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try
	        {
	            date = simpleDateFormat.parse(reservation.getReservationDateTime().split("T")[0]);
	           if(now.before(date)) {
	            	reservationsToReturn.add(reservation);
	            }
	        }
	        catch (ParseException ex)
	        {
	        	System.out.println("Exception "+ex);
	        }
			
		}
		
		return reservationsToReturn;
	}

	@Override
	public String cancelReservation(Reservation reservation) {
		if(reservation == null || reservation.getId() == null) 
			return Message.REQUESTERROR;
		
		List<Invite> invites = inviteDao.findByReservation(reservation);
		for(Invite invite : invites) {
			inviteDao.delete(invite);
		}
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
		
		
		sendEmail(invite.getFriend().getUser().getEmail(), invite.getFriend().getUser().getId());
		
		return Message.ERRORFREE;
		
	}
	
	private void sendEmail(String email, Integer userId) {
		
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("Poziv od strane prijatelja");
		message.setFrom("isasms2016@gmail.com");
		message.setTo(email);
		message.setText("http://localhost:8080/guest/invite/" + userId);

		try {
			mailSender.send(message);
		} catch (MailException e) {
			System.out.println(e.toString());
		}
		
	}

	@Override
	public String acceptInvite(Invite invite) {
		if(invite == null || invite.getId() == null)
			return Message.REQUESTERROR;
		
		
		invite.setAccepted(true);
		
		inviteDao.save(invite);
		
		return Message.ERRORFREE;
		
	}

	@Override
	public String declineInvite(Invite invite) {
		if(invite == null || invite.getId() == null)
			return Message.REQUESTERROR;
		
		inviteDao.delete(invite);
		
		return Message.ERRORFREE;
		
	}

	@Override
	public List<Invite> getFriendInvites(Integer userId) {
		if(userId == null)
			return new ArrayList<Invite>();
		
		Guest guest = guestDao.findByUserId(userId);
		
		return inviteDao.findByFriend(guest);
	}

	@Override
	public List<History> getGuestHistory(Integer userId) {
		if(userId == null){
			System.out.println("rsaras");
			return new ArrayList<History>();
		}
		
		Guest guest = guestDao.findByUserId(userId);
		List<Invite> guestInvited = inviteDao.findByFriend(guest);
		List<Invite> guestInvites = inviteDao.findByGuest(guest);
		List<History> histories = new ArrayList<History>();
		
		Map<Integer, List<Guest>> attendesMap = new HashMap<Integer, List<Guest>>();
		
		List<Reservation> guestReservations = reservationDao.findByGuest(guest);
		for(Reservation reservation : guestReservations) {
			attendesMap.put(reservation.getId(), new ArrayList<Guest>()); 
		}
		
		for(Invite invite : guestInvites) {
			if(invite.getAccepted()) {
				attendesMap.get(invite.getReservation().getId()).add(invite.getFriend()); 
			}
		}
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(Invite invite : guestInvited) {
			if(invite.getAccepted())
				reservations.add(invite.getReservation());
		}
		
		for(Reservation reservation : reservations) {
			attendesMap.put(reservation.getId(), new ArrayList<Guest>()); 
			List<Invite> invites = inviteDao.findByReservation(reservation);
			attendesMap.get(reservation.getId()).add(reservation.getGuest()); 
			for(Invite invite : invites) {
				
				if(!invite.getFriend().getId().equals(guest.getId()))
					attendesMap.get(invite.getReservation().getId()).add(invite.getFriend()); 
			}
			
		}
		
		Date now = new Date();
		
		@SuppressWarnings("rawtypes")
		Iterator it = attendesMap.entrySet().iterator();
	    while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry)it.next();
	        
	        Reservation reservation = reservationDao.findById((Integer) pair.getKey());
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try
	        {
	           date = simpleDateFormat.parse(reservation.getReservationDateTime().split("T")[0]);
	           if(now.after(date)) {
	        	   @SuppressWarnings("unchecked")
	   				List<Guest> values = (List<Guest>) pair.getValue();
	   	        	histories.add(new History(reservation.getRestaurant(), reservation.getReservationDateTime(), values));
	            }
	        }
	        catch (ParseException ex)
	        {
	        	System.out.println("Exception "+ex);
	        }
	      
	        it.remove(); 
	    }
		
		
		return histories;
	}

}
