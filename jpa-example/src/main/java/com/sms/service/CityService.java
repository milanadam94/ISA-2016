package com.sms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sms.beans.City;
import com.sms.beans.HotelSummary;

public interface CityService {

	Page<City> findCities(String criteria, Pageable pageable);

	City getCity(String name, String country);

	Page<HotelSummary> getHotels(City city, Pageable pageable);

}
