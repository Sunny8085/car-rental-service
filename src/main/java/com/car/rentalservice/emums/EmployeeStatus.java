package com.car.rentalservice.emums;

public enum EmployeeStatus {
	
	Active("E001"),
	Inactive("E002"),
	Suspended("E003");
	
	private final String code;
	
	EmployeeStatus(String code) {
        this.code = code;
    }
	
	public String getCode() {
        return code;
    }
	
}
