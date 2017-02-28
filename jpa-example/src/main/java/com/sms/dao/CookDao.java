package com.sms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Bartender;
import com.sms.beans.Cook;

public interface CookDao extends Repository<Cook, Long>{
	
	public Page<Cook> findAll(Pageable pageable);
	
	public Cook findById(Integer id);
	
	public Cook save(Cook cook);

}