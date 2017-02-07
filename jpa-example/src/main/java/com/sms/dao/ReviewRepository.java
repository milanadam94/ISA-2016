package com.sms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.sms.beans.Hotel;
import com.sms.beans.Review;

public interface ReviewRepository extends Repository<Review, Long> {

	public Page<Review> findByHotel(Hotel hotel, Pageable pageable);

	public Review findByHotelAndIndex(Hotel hotel, int index);

	public Review save(Review review);

}
