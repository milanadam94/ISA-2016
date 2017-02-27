package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Offerer;
import com.sms.beans.Offerings;
import com.sms.beans.Tender;

public interface OfferingsDao extends Repository<Offerings, Long>{
	
	public Offerings findById(Integer id);
	
	public Offerings save(Offerings user);

	public List<Offerings> findByOfferer(Offerer offerer);
	
	public void delete(Offerings offerings);
	
	public List<Offerings> findByTender(Tender tender);
}
