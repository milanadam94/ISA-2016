package com.sms.service;

import com.sms.beans.SysUser;

public interface UserService {

	public String validateLogin(SysUser user);

	public String validateRegistration(SysUser user);
}
