package com.qsp.onlineHotelBooking.exeption;

public class RoomNotFoundException  extends RuntimeException{
	
	private String message;

	public RoomNotFoundException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	

}
