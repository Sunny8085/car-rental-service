package com.car.rentalservice.emums;

public enum CustomerStatus {
	
	Active("C001"),
	Inactive("C002"),
	Suspended("C003");
	
	private final String code;
	
	CustomerStatus(String code) {
        this.code = code;
    }
	
	public String getCode() {
        return code;
    }
	
}
