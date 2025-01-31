package com.car.rentalservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclesDto {
	
	@NotBlank(message = "Manufacturer is required.")
	private String manufacturer;

	@NotBlank(message = "Model is required.")
	private String model;
	
	@NotBlank(message = "Year is required.")
	@Min(value = 1886, message = "Year must be a valid year.")
	private String year;

	@NotBlank(message = "Vehicle Identification Number (VIN) is required.")
	private String vehicleIdentNumber;

	@NotBlank(message = "License Plate is required.")
	private String licensePlate;

	@NotBlank(message = "Rental Rate is required.")
	@Digits(integer = 10, fraction = 2, message = "Rental rate must be a valid number.")
	private String rentalRate;
	
	@NotBlank(message = "Vehicle Type not found.")
	private String vehicleType;
	
	@NotBlank(message = "LocationName not found.")
	private String locationName;
	
	@NotBlank(message = "Address not found.")
	private String address;
	
	@NotBlank(message = "City not found.")
	private String city;
	
	@NotBlank(message = "State not found.")
	private String state;
	
	@NotBlank(message = "Pincode not found.")
	private String pincode;
	
	@NotNull(message = "Geometry point is required.")
	private String carGeom;
	
}
