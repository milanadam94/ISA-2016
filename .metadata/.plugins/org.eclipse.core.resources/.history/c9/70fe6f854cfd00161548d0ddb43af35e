package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Restaurant;
import com.sms.beans.Segment;

public interface SegmentDao  extends Repository<Segment, Long>{
	
	public Segment findById(Integer id);
	
	public void save(Segment segment);

	public Segment findByNameAndRestaurant(String name, Restaurant restaurant);
}
