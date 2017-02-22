package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Active_User")
public class ActiveUser implements Serializable {

	private static final long serialVersionUID = -4523614876931390755L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private SysUser user;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "user_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	public ActiveUser() {

	}

	public ActiveUser(SysUser user, String email, UserType userType) {
		this.user = user;
		this.email = email;
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
