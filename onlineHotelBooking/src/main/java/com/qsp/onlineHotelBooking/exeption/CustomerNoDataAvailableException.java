package com.qsp.onlineHotelBooking.exeption;

public class CustomerNoDataAvailableException extends RuntimeException {


	private String message;

	public CustomerNoDataAvailableException(String message) {

		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
