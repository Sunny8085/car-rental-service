package com.car.rentalservice.emums;

public enum Role {
	
	ADMIN("ADM001"),
    USER("USR001"),
    EMPLOYEE("EMP001"),
    GUEST("GST001"),
    CAROWNER("CO001");
	
	private final String code;
	
	Role(String code) {
        this.code = code;
    }
	
	public String getCode() {
        return code;
    }
	
}
