package com.qsp.onlineHotelBooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.onlineHotelBooking.dto.Hotel;
import com.qsp.onlineHotelBooking.repo.HotelRepo;

@Repository
public class HotelDAO {

	@Autowired
	private HotelRepo repo;
	
	public Hotel saveHotel(Hotel hotel) {
		return repo.save(hotel);
	}
	
	public Hotel hotelLogin(String email, String password) {
		return repo.findByHotelEmail(email);
	}
	
	public Optional<Hotel> findById(int hotelId) {
		return repo.findById(hotelId);		
	}
	
//	public Optional<Hotel> updateRoomPrice(int hotelId, double newPrice) {
//		return repo.save(null)
//	}
	
	public Optional<Hotel> updateTotalRooms(int hotelId, int newTotalRooms) {
		return repo.findById(hotelId);
	}
	
	public List<Hotel> findHotelByName(String hotelName) {
		return repo.findByHotelName(hotelName);
	}
	
	public List<Hotel> findHotelByLoc(String hotelLoc) {
		return repo.findByHotelLocation(hotelLoc);
	}
	
	public Hotel deleteHotel(int hotelId) {
		Optional<Hotel> op = repo.findById(hotelId);
		if (op.isPresent()) {
			Hotel hotel = op.get();
			repo.delete(hotel);	
			return hotel;
		}
		return null;
	}

	public Hotel updateRoomPrice(Hotel hotel) {
		// TODO Auto-generated method stub
		
		
		
		return repo.save(hotel);
	}
	
	
	
	public Optional<Hotel> updateAvailRoom(int hotelId, int newAvailRoom) {
		return repo.findById(hotelId);
	}
	
	public Optional<Hotel> updatePassword(int hotelId, String oldPass, String newPass) {
		return repo.findById(hotelId);
	}

	public Optional<Hotel> updatePhone(int hotelId, long oldPhone, long newPhone) {
		return repo.findById(hotelId);
	}


}
