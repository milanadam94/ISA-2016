package com.sms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Tender;

public interface TenderDao extends Repository<Tender, Long>{
	
	public Page<Tender> findAll(Pageable pageable);

	public Tender findById(Integer id);
	
	public void save(Tender tender);
	
	//public Tender findByDescriptionAndStartDateAndEndDate(String )

	
}
