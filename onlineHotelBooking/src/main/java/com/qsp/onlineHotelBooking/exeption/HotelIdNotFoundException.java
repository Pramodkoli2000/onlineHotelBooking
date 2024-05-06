package com.qsp.onlineHotelBooking.exeption;

public class HotelIdNotFoundException extends RuntimeException{
	
	String message;

	public HotelIdNotFoundException(String message) {
		
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	

}
