package com.qsp.onlineHotelBooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.onlineHotelBooking.dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	Customer findByCustomerEmail(String customerEmail);
}
