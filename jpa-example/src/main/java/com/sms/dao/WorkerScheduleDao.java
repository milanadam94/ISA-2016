package com.sms.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sms.beans.Schedule;
import com.sms.beans.SysUser;
import com.sms.beans.WorkerSchedule;

public interface WorkerScheduleDao extends Repository<WorkerSchedule, Long>{
	
	public void delete(WorkerSchedule workerSchedule);
	
	public void save(WorkerSchedule workerSchedule);
	
	public List<WorkerSchedule> findBySchedule(Schedule schedule);
	
	public List<WorkerSchedule> findByWorker(SysUser worker);
	
}

