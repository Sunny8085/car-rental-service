package com.car.rentalservice.emums;

public enum ReservationStatusCode {
	
	PENDING(100),
	CONFIRMED(200),
	IN_PROGRESS(300),
	CANCELLED(400),
	COMPLETED(500),
	NO_SHOW(600),
	EXTENDED(700),
	AWAITING_PAYMENT(800);
	
	final int status_code;
	
	ReservationStatusCode(int status_code){
		this.status_code = status_code;
	}

	public int getStatus_code() {
		return status_code;
	}
	
}
