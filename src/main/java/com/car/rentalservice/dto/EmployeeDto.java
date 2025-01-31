package com.car.rentalservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDto {
	
	@NotBlank(message = "Employee name cannot be empty")
	private String name;
	
	@NotBlank(message = "Employee email cannot be empty")
	private String email;
	
	@NotBlank(message = "Employee phone cannot be empty")
	private String phone;
	
	@NotBlank(message = "Employee position cannot be empty")
	private String position;
	
	@NotBlank(message = "Employee driving license cannot be empty")
	private String driverLicense;
	
	@NotBlank(message = "DOB cannot be empty")
	private String dateOfBirth;
	
	@NotBlank(message = "Employee address cannot be empty")
	private String employeeAddress;
	
	@NotBlank(message = "Employee district cannot be empty")
	private String district;
	
	@NotBlank(message = "Employee country cannot be empty")
	private String country;
	
	@NotBlank(message = "Employee state cannot be empty")
	private String state;
	
	@NotBlank(message = "Employee pincode cannot be empty")
	private String pincode;
	
}
