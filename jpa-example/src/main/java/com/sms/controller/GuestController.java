package com.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.sms.beans.Guest;
import com.sms.beans.History;
import com.sms.beans.Invite;
import com.sms.beans.Reservation;
import com.sms.service.GuestService;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	@PostMapping(path = "/loadGuest/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Guest loadGuest(@PathVariable(value="userId") Integer userId) {
		
		return guestService.getGuestByUserId(userId);
	}
	
	@PostMapping(path = "/loadGuestReservations/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Reservation> loadGuestReservations(@PathVariable(value="userId") Integer userId) {
		return guestService.loadGuestReservations(userId);
	}
	
	@PostMapping(path = "/loadGuestInvites/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Invite> loadGuestInvites(@PathVariable(value="userId") Integer userId) {
		return guestService.loadGuestInvites(userId);
	}
	
	@PostMapping(path = "/searchGuests/{guestId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Guest> searchGuests(@RequestBody(required = false) String searchInput, @PathVariable(value="guestId") Integer guestId) {
		
		return guestService.searchGuests(guestId, searchInput);
	}

	@PostMapping(path = "/addFriend/{guestId}", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String addFriend(@RequestBody Integer friendId, @PathVariable(value="guestId") Integer guestId) {
		
		return guestService.addFriend(guestId, friendId);
	}
	
	@PostMapping(path = "/acceptRequest/{guestId}", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String acceptRequest(@RequestBody Integer friendId, @PathVariable(value="guestId") Integer guestId) {
		
		return guestService.acceptRequest(guestId, friendId);
	}
	
	@PostMapping(path = "/declineRequest/{guestId}", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String declineRequest(@RequestBody Integer friendId, @PathVariable(value="guestId") Integer guestId) {
		
		return guestService.declineRequest(guestId, friendId);
	}
	
	@PostMapping(path = "/removeFriend/{guestId}", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String removeFriend(@RequestBody Integer friendId, @PathVariable(value="guestId") Integer guestId) {
		
		return guestService.removeFriend(guestId, friendId);
	}
	
	@PostMapping(path = "/editProfile", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String editProfile(@RequestBody Guest guest) {
		
		return guestService.editProfile(guest);
	}
	
	@PostMapping(path = "/inviteFriend", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String inviteFriend(@RequestBody Invite invite) {
		
		return guestService.inviteFriend(invite);
	}
	
	//invites - u invite controller
	@GetMapping(path = "/invite/{userId}")
	@ResponseBody
	public RedirectView invite(@PathVariable(value="userId") Integer userId) {
		
		return new RedirectView("/InvitePage/InvitePage.html");
	}
	@PostMapping(path = "/acceptInvite",  produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String acceptInvite(@RequestBody Invite invite) {
		
		return guestService.acceptInvite(invite);
	}
	
	@PostMapping(path = "/declineInvite",  produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String declineInvite(@RequestBody Invite invite) {
		
		return guestService.declineInvite(invite);
	}
	
	@PostMapping(path = "/getFriendInvites/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Invite> getFriendInvites(@PathVariable(value="userId") Integer userId) {
		return guestService.getFriendInvites(userId);
	}
	
	@PostMapping(path = "/getGuestHistory/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<History> getGuestHistory(@PathVariable(value="userId") Integer userId) {
		return guestService.getGuestHistory(userId);
	}
	
	
	
	@PostMapping(path = "/cancelReservation", produces=MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String removeFriend(@RequestBody Reservation reservation) {
		
		return guestService.cancelReservation(reservation);
	}
}
