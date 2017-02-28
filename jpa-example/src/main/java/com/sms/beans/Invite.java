package com.sms.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Invite")
public class Invite implements Serializable {

	private static final long serialVersionUID = -8143625777282403006L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Guest guest;

	@ManyToOne
	private Guest friend;

	@ManyToOne
	private Reservation reservation;

	@Column(name = "accepted")
	private Boolean accepted;

	public Invite() {
	}

	public Invite(Guest guest, Guest friend, Reservation reservation, Boolean accepted) {
		this.guest = guest;
		this.friend = friend;
		this.reservation = reservation;
		this.accepted = accepted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Guest getFriend() {
		return friend;
	}

	public void setFriend(Guest friend) {
		this.friend = friend;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

}
