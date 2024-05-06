package com.qsp.onlineHotelBooking.exeption;

public class CustomerPhoneNotFoundException extends RuntimeException {
	
	private String message;

	public CustomerPhoneNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	

}
