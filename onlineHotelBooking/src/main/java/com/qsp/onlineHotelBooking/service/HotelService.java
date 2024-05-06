package com.qsp.onlineHotelBooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.onlineHotelBooking.dao.HotelDAO;
import com.qsp.onlineHotelBooking.dto.Hotel;
import com.qsp.onlineHotelBooking.exeption.HotelDataNotFoundException;
import com.qsp.onlineHotelBooking.exeption.HotelEmailNotFoundException;
import com.qsp.onlineHotelBooking.exeption.HotelIdNotFoundException;
import com.qsp.onlineHotelBooking.exeption.HotelLocationNotFoundException;
import com.qsp.onlineHotelBooking.exeption.RoomNotFoundException;
import com.qsp.onlineHotelBooking.util.ResponseStructure;

@Service
public class HotelService {

	@Autowired
	private HotelDAO dao;

	public ResponseEntity<ResponseStructure<Hotel>> saveHotel(Hotel hotel) {

		ResponseStructure<Hotel> structure = new ResponseStructure<Hotel>();
		structure.setMassage("Save Hotel Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveHotel(hotel));

		return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Hotel>> hotelLogin(String email, String password) {
		Hotel dbHotel = dao.hotelLogin(email, password);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (dbHotel != null) {
			if (dbHotel.getHotelPassword().equals(password)) {

				structure.setMassage("Login Success!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbHotel);
				return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
			} else {
				structure.setMassage("Login Failed! Incorrect password.");
				structure.setStatus(HttpStatus.UNAUTHORIZED.value());
				return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.UNAUTHORIZED);
			}
		} else {
			throw new HotelEmailNotFoundException("Hotel with the given email " + email + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Hotel>> updateRoomPrice(int hotelId, double newPrice) {
		Optional<Hotel> op = dao.findById(hotelId);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (op.isPresent()) {
			Hotel hotel = op.get();
			hotel.setRoomPrice(newPrice);
			Hotel hotel1 = dao.updateRoomPrice(hotel);
			structure.setMassage("Login Success!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(hotel1);
			return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
		} else {
			throw new HotelIdNotFoundException("Hotel with the given Id " + hotelId + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Hotel>> updateTotalRooms(int hotelId, int newTotalRooms) {
		Optional<Hotel> op = dao.updateTotalRooms(hotelId, newTotalRooms);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (op.isPresent()) {
			Hotel hotel = op.get();
			hotel.setTotalRooms(newTotalRooms);
			Hotel hotel1 = dao.saveHotel(hotel);
			structure.setMassage("Rooms Updated Success!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(hotel1);
			return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
		} else {
			throw new HotelIdNotFoundException("Hotel with the given Id " + hotelId + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Hotel>> deleteHotel(int hotelId) {
		Hotel hotel = dao.deleteHotel(hotelId);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (hotel != null) {

			structure.setMassage("  Remove Hotel Success!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(hotel);
			return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
		} else {
			throw new HotelIdNotFoundException("Hotel with the given Id " + hotelId + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Hotel>>> findHotelByName(String hotelName) {
		List<Hotel> list = dao.findHotelByName(hotelName);
		ResponseStructure<List<Hotel>> structure = new ResponseStructure<>();

		if (list != null) {
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Hotel>>>(structure, HttpStatus.FOUND);
		}
		throw new HotelDataNotFoundException("Hotel with the given Data " + hotelName + " not found");
	}

	public ResponseEntity<ResponseStructure<List<Hotel>>> findHotelByLoc(String hotelLoc) {
		List<Hotel> list = dao.findHotelByLoc(hotelLoc);
		ResponseStructure<List<Hotel>> structure = new ResponseStructure<>();
		if (list != null) {
			structure.setMassage("Data found successfully!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(list);
			return new ResponseEntity<ResponseStructure<List<Hotel>>>(structure, HttpStatus.FOUND);
		}
		throw new HotelLocationNotFoundException("Hotel with the given Location " + hotelLoc + " not found");
	}

	public ResponseEntity<ResponseStructure<Hotel>> updateAvailRoom(int hotelId, int newAvailRoom) {
		Optional<Hotel> op = dao.updateAvailRoom(hotelId, newAvailRoom);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (op.isPresent()) {
			Hotel hotel = op.get();
			hotel.setAvailRoom(newAvailRoom);

			structure.setMassage("Update Available Rooms Success!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveHotel(hotel));
			return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
		} else {
			throw new RoomNotFoundException("Number of availables rooms is exceeding number of booked rooms");
		}
	}

	public ResponseEntity<ResponseStructure<Hotel>> updatePassword(int hotelId, String oldPass, String newPass) {
		Optional<Hotel> op = dao.updatePassword(hotelId, oldPass, newPass);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (op.isPresent()) {
			Hotel hotel = op.get();
			String dbPass = hotel.getHotelPassword();
			if (dbPass.equals(oldPass)) {
				hotel.setHotelPassword(newPass);

				structure.setMassage("Password Updated Success!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveHotel(hotel));
				return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
			} else {

				throw new HotelDataNotFoundException("Password Not Updated!\nIncoorect Current Password!");
			}
		} else {
			throw new HotelIdNotFoundException("Hotel with the given Id " + hotelId + " not found");
		}
	}

	public ResponseEntity<ResponseStructure<Hotel>> updatePhone(int hotelId, long oldPhone, long newPhone) {
		Optional<Hotel> op = dao.updatePhone(hotelId, oldPhone, newPhone);
		ResponseStructure<Hotel> structure = new ResponseStructure<>();
		if (op.isPresent()) {
			Hotel hotel = op.get();
			long dbPhone = hotel.getHotelPhone();
			if (dbPhone == oldPhone) {
				hotel.setHotelPhone(newPhone);
				structure.setMassage("Phone Updated Success!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveHotel(hotel));
				return new ResponseEntity<ResponseStructure<Hotel>>(structure, HttpStatus.OK);
			} else {
				throw new HotelDataNotFoundException("Phone Not Updated! Incorrect Current Phone Number!");
			}
		} else {
			throw new HotelIdNotFoundException("Hotel with the given Id " + hotelId + " not found");
		}

	}

}
