package com.sms.dao;

import org.springframework.data.repository.Repository;

import com.sms.beans.SysUserActivation;

public interface SysUserActivationDao  extends Repository<SysUserActivation, Long> {
	
	public SysUserActivation findByEmail(String email);
	
	public SysUserActivation findByActivationCode(String activationCode);
	
	public SysUserActivation save(SysUserActivation sysUserActivation);
	
	public void delete(SysUserActivation sysUserActivation);
	
}
