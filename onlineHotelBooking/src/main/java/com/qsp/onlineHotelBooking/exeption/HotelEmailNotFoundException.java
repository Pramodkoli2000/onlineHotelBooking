package com.qsp.onlineHotelBooking.exeption;

public class HotelEmailNotFoundException extends RuntimeException {

	private String message;

	public HotelEmailNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
