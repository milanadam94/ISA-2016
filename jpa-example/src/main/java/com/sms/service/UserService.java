package com.sms.service;

import com.sms.beans.SysUser;

public interface UserService {

	public SysUser login(SysUser user);

	public String register(SysUser user);
}
