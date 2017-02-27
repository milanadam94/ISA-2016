package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Friendship;

public interface FriendshipDao extends Repository<Friendship, Long> {
	
	public List<Friendship> findByGuest(Integer guestId);
	
	public void delete(Friendship friendship);
    
	public Friendship save(Friendship friendship);

}
