package com.qsp.onlineHotelBooking.exeption;

public class CustomerIdNotFoundException extends RuntimeException {
	private String message;

	public CustomerIdNotFoundException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
