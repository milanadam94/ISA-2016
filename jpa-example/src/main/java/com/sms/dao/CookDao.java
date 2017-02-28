package com.sms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Cook;
import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;

public interface CookDao extends Repository<Cook, Long>{
	
	public Page<Cook> findAll(Pageable pageable);
	
	public List<Cook> findByRestaurant(Restaurant restaurant);
	
	public Cook findById(Integer id);
	
	public Cook save(Cook cook);
	
	public Cook findByUser(SysUser user);

	public Cook findByUserId(Integer userId);
}