package com.qsp.onlineHotelBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.onlineHotelBooking.dto.Hotel;
import com.qsp.onlineHotelBooking.service.HotelService;
import com.qsp.onlineHotelBooking.util.ResponseStructure;

@RestController
public class HotelController {

	@Autowired
	private HotelService service;

	@PostMapping("/save/hotel")
	public ResponseEntity<ResponseStructure<Hotel>> saveHotel(@RequestBody Hotel hotel) {
		return service.saveHotel(hotel);
	}

	@GetMapping("/login/hotel")
	public ResponseEntity<ResponseStructure<Hotel>> hotelLogin(@RequestParam String email,
			@RequestParam String password) {
		return service.hotelLogin(email, password);
	}

	@PatchMapping("/update/price")
	public ResponseEntity<ResponseStructure<Hotel>> updateRoomPrice(@RequestParam int hotelId,
			@RequestParam double newPrice) {
		return service.updateRoomPrice(hotelId, newPrice);
	}

	@PatchMapping("/update/room")
	public ResponseEntity<ResponseStructure<Hotel>> updateTotalRooms(@RequestParam int hotelId,
			@RequestParam int newTotalRooms) {
		return service.updateTotalRooms(hotelId, newTotalRooms);
	}

	@GetMapping("/find/name")
	public ResponseEntity<ResponseStructure<List<Hotel>>> findHotelByName(@RequestParam String hotelName) {
		return service.findHotelByName(hotelName);
	}

	@GetMapping("/find/location")
	public ResponseEntity<ResponseStructure<List<Hotel>>> findHotelByLoc(@RequestParam String hotelLoc) {
		return service.findHotelByLoc(hotelLoc);
	}

	@DeleteMapping("/delete/hotel")
	public ResponseEntity<ResponseStructure<Hotel>> deleteHotel(@RequestParam int hotelId) {
		return service.deleteHotel(hotelId);
	}

	@PatchMapping("/update/avail/room")
	public ResponseEntity<ResponseStructure<Hotel>> updateAvailRoom(int hotelId, int newAvailRoom) {
		return service.updateAvailRoom(hotelId, newAvailRoom);
	}

	@PatchMapping("/update/hotel/password")
	public ResponseEntity<ResponseStructure<Hotel>> updatePassword(@RequestParam int hotelId,
			@RequestParam String oldPass, @RequestParam String newPass) {
		return service.updatePassword(hotelId, oldPass, newPass);
	}

	@PatchMapping("/update/hotel/phone")
	public ResponseEntity<ResponseStructure<Hotel>> updatePhone(@RequestParam int hotelId, @RequestParam long oldPhone,
			@RequestParam long newPhone) {
		return service.updatePhone(hotelId, oldPhone, newPhone);
	}  

}
