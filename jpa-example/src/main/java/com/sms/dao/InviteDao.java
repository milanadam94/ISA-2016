package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;
import com.sms.beans.Invite;
import com.sms.beans.Reservation;

public interface InviteDao extends Repository<Invite, Long>  {

	public List<Invite> findByGuest(Guest guest);
	
	public Invite save(Invite invite);

	public List<Invite> findByFriend(Guest friend);

	public void delete(Invite invite);

	public List<Invite> findByReservation(Reservation reservation);

}
