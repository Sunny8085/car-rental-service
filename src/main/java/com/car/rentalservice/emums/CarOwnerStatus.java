package com.car.rentalservice.emums;

public enum CarOwnerStatus {
	
	Active("CR01"),
	Inactive("CR02"),
	Suspended("CR03");
	
	private final String code;
	
	CarOwnerStatus(String code) {
        this.code = code;
    }
	
	public String getCode() {
        return code;
    }
	
}
