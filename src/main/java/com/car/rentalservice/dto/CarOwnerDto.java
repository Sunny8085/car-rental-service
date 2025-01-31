package com.car.rentalservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarOwnerDto {
	
	@NotBlank(message = "User name cannot be empty")
    private String name;

    @Email(message = "User email cannot be empty")
    private String email;

    @NotBlank(message = "User phone cannot be empty")
    private String phone;

    @NotBlank(message = "User address cannot be empty")
    private String address;

    @NotBlank(message = "Driver license number cannot be empty")
    private String driverLicenseNumber;

    @NotBlank(message = "Date of birth cannot be empty")
    private String dateOfBirth;
    
    @NotBlank(message = "District cannot be empty")
    private String district;
	
    @NotBlank(message = "Country cannot be empty")
	private String country;
	
    @NotBlank(message = "State cannot be empty")
	private String state;
	
    @NotBlank(message = "Pincode cannot be empty")
	private String pincode;
	
}
