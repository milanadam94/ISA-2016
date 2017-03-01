package com.sms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.SysUser;

public interface UserDao extends Repository<SysUser, Long>{
	
	public Page<SysUser> findAll(Pageable pageable);
	
	public List<SysUser> findAll();

	public SysUser findByEmailAndPassword(String email, String password);

	public SysUser findByEmail(String email);
	
	public SysUser findById(Integer id);
	
	public SysUser save(SysUser user);

}
