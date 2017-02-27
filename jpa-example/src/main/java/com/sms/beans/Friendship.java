package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {

	private static final long serialVersionUID = -1501880877048234981L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "guest", nullable = false)
	private Integer guest;

	@Column(name = "friend", nullable = false)
	private Integer friend;

	public Friendship() {
	}

	public Friendship(Integer guest, Integer friend) {
		this.guest = guest;
		this.friend = friend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGuest() {
		return guest;
	}

	public void setGuest(Integer guest) {
		this.guest = guest;
	}

	public Integer getFriend() {
		return friend;
	}

	public void setFriend(Integer friend) {
		this.friend = friend;
	}

}
