package com.sms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.sms.beans.Guest;

public interface GuestDao extends Repository<Guest, Integer> {

	public Guest save(Guest guest);
	
	public Guest findByUserId(Integer user);
	
	public Guest findById(Integer id);
	
	@Query("from Guest where (lower(concat(user.name,user.lastName)) like %:searchInput% or lower(concat(user.lastName,user.name)) like %:searchInput%) and id != :guestId")
    public List<Guest> searchGuests(@Param("guestId") Integer guestId, @Param("searchInput")String searchInput);

	
}
