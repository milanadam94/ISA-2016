package com.sms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sms.beans.City;
import com.sms.beans.Hotel;
import com.sms.beans.Review;
import com.sms.beans.ReviewDetails;

public interface HotelService {

	Hotel getHotel(City city, String name);

	Page<Review> getReviews(Hotel hotel, Pageable pageable);

	Review getReview(Hotel hotel, int index);

	Review addReview(Hotel hotel, ReviewDetails details);

}
