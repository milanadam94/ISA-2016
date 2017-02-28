package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="worker_schedule")
public class WorkerSchedule implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7386515759987873332L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	
	@ManyToOne
	private Schedule schedule;
	
	@ManyToOne
	private SysUser worker;

	
	public WorkerSchedule(){
		
	}
	
	public WorkerSchedule(Schedule schedule, SysUser worker) {
		super();
		this.schedule = schedule;
		this.worker = worker;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public SysUser getWorker() {
		return worker;
	}

	public void setWorker(SysUser worker) {
		this.worker = worker;
	}
	
	
	
}
