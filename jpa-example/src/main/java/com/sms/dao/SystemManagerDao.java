package com.sms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Restaurant;
import com.sms.beans.SysUser;
import com.sms.beans.UserType;

public interface SystemManagerDao extends Repository<SysUser, Long>{
	
	
	public Page<SysUser> findAll(Pageable pageable);

	public SysUser findByEmailAndPassword(String email, String password);

	public SysUser findByEmail(String email);
	
	public SysUser save(SysUser user);
	
	public List<SysUser> findByUserType(UserType userType); 
	
	public Restaurant save(Restaurant rest);
	
	public Restaurant findByName(String name);
	
}
