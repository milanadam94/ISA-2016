package com.sms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Bartender;
import com.sms.beans.Restaurant;

public interface BartenderDao extends Repository<Bartender, Long>{
	
	public Page<Bartender> findAll(Pageable pageable);
	
	public List<Bartender> findByRestaurant(Restaurant restaurant);
	
	public Bartender findById(Integer id);
	
	public Bartender save(Bartender bartender);

}