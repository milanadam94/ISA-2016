package com.sms.service;

import com.sms.beans.Guest;

public interface GuestService {

	public Guest getGuestByUserId(Integer userId);

	public String editProfile(Guest guest);

}
