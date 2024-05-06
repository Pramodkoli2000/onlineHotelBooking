package com.qsp.onlineHotelBooking.exeption;

public class CustomerEmailNotFoundException extends RuntimeException {

	private String message;

	public CustomerEmailNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
