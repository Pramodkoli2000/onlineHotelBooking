package com.qsp.onlineHotelBooking.exeption;

public class HotelLocationNotFoundException extends RuntimeException{
	
	private String message;

	public HotelLocationNotFoundException(String message) {
	
		this.message = message;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	
	

}
