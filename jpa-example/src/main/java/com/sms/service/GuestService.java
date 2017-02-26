package com.sms.service;

import java.util.List;

import com.sms.beans.Guest;

public interface GuestService {

	public Guest getGuestByUserId(Integer userId);

	public String editProfile(Guest guest);

	public List<Guest> searchGuests(Integer guestId, String searchInput);

	public String addFriend(Integer guestId, Guest friend);

}
