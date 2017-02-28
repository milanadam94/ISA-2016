package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.Schedule;

public interface ScheduleDao extends Repository<Schedule, Long>{
	
	public Schedule findById(Integer id);
	
	public Schedule save(Schedule cook);
	
}
