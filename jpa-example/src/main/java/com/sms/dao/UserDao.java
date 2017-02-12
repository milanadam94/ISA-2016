package com.sms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.SysUser;

public interface UserDao extends Repository<SysUser, Long>{
	
	public Page<SysUser> findAll(Pageable pageable);

	public SysUser findByEmailAndPassword(String email, String password);

}