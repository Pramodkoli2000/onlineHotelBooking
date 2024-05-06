package com.qsp.onlineHotelBooking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.onlineHotelBooking.dto.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Integer>{

	Hotel findByHotelEmail(String hotelEmail);
	List<Hotel> findByHotelName(String hotelName);
	List<Hotel> findByHotelLocation(String hotelLocation);
	Optional<Hotel> findById(int id);
}
