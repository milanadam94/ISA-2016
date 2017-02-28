package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Guest;
import com.sms.beans.Invite;

public interface InviteDao extends Repository<Invite, Long>  {

	public List<Invite> findByGuest(Guest guest);
	
	public Invite save(Invite invite);

}
