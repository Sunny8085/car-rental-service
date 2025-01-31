package com.car.rentalservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersDto {
	
	@NotBlank(message = "User Id cannot be empty")
	private String username;
	
	@NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	@NotBlank(message = "Mobile No cannot be empty")
	private String mobileNo;
	
}
