package com.qsp.onlineHotelBooking.exeption;

public class HotelDataNotFoundException extends RuntimeException{
	private String message;

	public HotelDataNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
