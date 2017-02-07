package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sms.beans.Hotel;
import com.sms.service.CityService;
import com.sms.service.HotelService;

@Controller
public class SampleController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private HotelService hotelService;

	@GetMapping("/city")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getCityName() {
		return this.cityService.getCity("Bath", "UK").getName();
	}
	
	@GetMapping("/hotel")
	@ResponseBody
	@Transactional(readOnly = true)
	public String getReview() {
		Hotel hotel = hotelService.getHotel(this.cityService.getCity("Bath", "UK"), "The Bath Priory Hotel");
		return this.hotelService.getReview(hotel, 5).getTitle() + "-" + this.hotelService.getReview(hotel, 5).getDetails();
	}
	

}
