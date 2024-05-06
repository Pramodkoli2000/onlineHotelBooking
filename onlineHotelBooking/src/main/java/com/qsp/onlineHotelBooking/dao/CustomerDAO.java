package com.qsp.onlineHotelBooking.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.onlineHotelBooking.dto.Customer;
import com.qsp.onlineHotelBooking.dto.Hotel;
import com.qsp.onlineHotelBooking.repo.CustomerRepo;
import com.qsp.onlineHotelBooking.repo.HotelRepo;

@Repository
public class CustomerDAO {

	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private HotelRepo repoHotel;
	
	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer loginCustomer(String email, String password) {
		return repo.findByCustomerEmail(email);
	}
	
	public Optional<Customer> findById(int customerId) {
		return repo.findById(customerId);
	}
	
	public Optional<Hotel> bookRoom(int hotelId, int noOfRoom) {
		return repoHotel.findById(hotelId);
	}
	
	public Optional<Customer> cancelRoom(int hotelId, int customerId, int noOfRoom) {
		return repo.findById(customerId);
	}
	
	
	
	
	public Optional<Customer> updatePhone(int customerId, long oldPhone, long newPhone) {
		return repo.findById(customerId);
	}
	
	public Optional<Customer> updateAddress(int customerId, String newAddress) {
		return repo.findById(customerId);
	}
	
//	public Optional<Customer> updateAadhar(int customerId, long newAadhar) {
//		return repo.findById(customerId);
//	}
	
	public Optional<Customer> updatePassword(int customerId, String oldPass, String newPass) {
		return repo.findById(customerId);
	}
	
	public Customer deleteCustomer(int customerId) {
		Optional<Customer> op = repo.findById(customerId);
		if (op.isPresent()) {
			Customer customer = op.get();
			repo.delete(customer);	
			return customer;
		}
		return null;
	}

}
