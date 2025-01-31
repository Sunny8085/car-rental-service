package com.car.rentalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.CarOwnerDto;
import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.service.CarOwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/carowner")
public class CarOwnerController {
	
	@Autowired
	private CarOwnerService carOwnerService;
	
	@Operation(summary = "Create a new Owner", description = "Create a new Owner")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created Owner")
    })
	@PostMapping("/{username}")
	public ResponseEntity<RestApiResponse> createCustomer(@Valid @RequestBody CarOwnerDto Owner,@PathVariable String username){
		return new ResponseEntity<>(
				new RestApiResponse(true,carOwnerService.createOwner(Owner,username)),HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Owner", description = "Update an existing Owner's details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully update Owner")
    })
	@PutMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> updateCustomer(@Valid @RequestBody CarOwnerDto Owner, @PathVariable Long customerId){
		return new ResponseEntity<>(
				new RestApiResponse(true,carOwnerService.updateOwner(Owner,customerId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Delete Owner", description = "Delete a Owner by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Delete Successfully")
    })
	@DeleteMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> deleteOwner(@PathVariable Long customerId){
		carOwnerService.deleteOwner(customerId);
		return new ResponseEntity<>(new RestApiResponse(true,"Owner Deleted successfully"),HttpStatus.OK);
	}
	
	@Operation(summary = "Get Owner Details", description = "Retrieve Owner details by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Owner details retrieved successfully")
    })
	@GetMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> getOwnerDetails(@PathVariable Long customerId){
		return new ResponseEntity<>(new RestApiResponse(true,carOwnerService.findOwner(customerId)),HttpStatus.OK);
	}
	
}
