package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Guest;
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

	@PostMapping(path = "/editProfile", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String editProfile(@RequestBody Guest guest) {
		
		return guestService.editProfile(guest);
		
	}
	
}
