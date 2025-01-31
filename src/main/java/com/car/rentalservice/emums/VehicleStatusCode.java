package com.car.rentalservice.emums;

public enum VehicleStatusCode {
	
	AVAILABLE("AV"),
    RENTED("RT"),
    MAINTENANCE("MT"),
    RESERVED("RS"),
    OUT_OF_SERVICE("OS"),
    CLEANING("CL"),
    INSPECTION("IN"),
    RETIRED("RTD"),
    LOST_STOLEN("LS"),
    PENDING_RETURN("PR"),
    DISPOSED("DP");
	
	private final String code;
	
	VehicleStatusCode(String code) {
        this.code = code;
    }

	public String getCode() {
		return code;
	}
	
}
