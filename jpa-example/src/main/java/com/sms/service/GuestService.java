package com.sms.service;

import java.util.List;

import com.sms.beans.Guest;
import com.sms.beans.History;
import com.sms.beans.Invite;
import com.sms.beans.Reservation;

public interface GuestService {

	public Guest getGuestByUserId(Integer userId);

	public String editProfile(Guest guest);

	public List<Guest> searchGuests(Integer guestId, String searchInput);

	public String addFriend(Integer guestId, Integer friendId);

	public String removeFriend(Integer guestId, Integer friendId);

	public String acceptRequest(Integer guestId, Integer friendId);

	public String declineRequest(Integer guestId, Integer friendId);

	public List<Reservation> loadGuestReservations(Integer userId);

	public String cancelReservation(Reservation reservation);

	public List<Invite> loadGuestInvites(Integer userId);

	public String inviteFriend(Invite invite);

	public String acceptInvite(Invite invite);

	public String declineInvite(Invite invite);

	public List<Invite> getFriendInvites(Integer userId);

	public List<History> getGuestHistory(Integer userId);

}
