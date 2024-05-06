package com.qsp.onlineHotelBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.onlineHotelBooking.dto.Customer;
import com.qsp.onlineHotelBooking.service.CustomerService;
import com.qsp.onlineHotelBooking.util.ResponseStructure;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/save/customer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@GetMapping("/login/customer")
	public ResponseEntity<ResponseStructure<Customer>> loginCustomer(@RequestParam String email,
			@RequestParam String password) {
		return service.loginCustomer(email, password);
	}

	@PostMapping("/book/room")
	public void bookRoom(int hotelId, int customerId, int noOfRoom,int roomId, int fromDate, int fromMonth, int fromYear, int toDate,
			int toMonth, int toYear) {
		 service.bookRoom(hotelId, customerId, noOfRoom, roomId, fromDate, fromMonth, fromYear, toDate, toMonth, toYear);
	}

	@PostMapping("/cancel/room")
	public ResponseEntity<ResponseStructure<Customer>> cancelRoom(int hotelId, int customerId, int noOfRoom) {
		return service.cancelRoom(hotelId, customerId, noOfRoom);
	}

	@PatchMapping("/update/customer/phone")
	public ResponseEntity<ResponseStructure<Customer>> updatePhone(@RequestParam int customerId,
			@RequestParam long oldPhone, @RequestParam long newPhone) {
		return service.updatePhone(customerId, oldPhone, newPhone);
	}

	@PatchMapping("/update/customer/address")
	public ResponseEntity<ResponseStructure<Customer>> updateAddress(@RequestParam int customerId,
			@RequestParam String newAddress) {
		return service.updateAddress(customerId, newAddress);
	}

	@PatchMapping("/update/customer/password")
	public ResponseEntity<ResponseStructure<Customer>> updatePassword(@RequestParam int customerId,
			@RequestParam String oldPass, @RequestParam String newPass) {
		return service.updatePassword(customerId, oldPass, newPass);
	}

	@DeleteMapping("/delete/customer")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int customerId) {
		return service.deleteCustomer(customerId);
	}

//	@PatchMapping("/update/customer/aadhar")
//	public Customer updateAadhar(@RequestParam int customerId, @RequestParam long newAadhar) {
//		return service.updateAadhar(customerId, newAadhar);
//	}

}
