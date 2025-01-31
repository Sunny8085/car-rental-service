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

import com.car.rentalservice.dto.CustomerDto;
import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.service.CustomersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/customers")
public class CustomersController {
	
	@Autowired
	private CustomersService customerService;
	
	@Operation(summary = "Create a new customer", description = "Create a new customer")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created customer")
    })
	@PostMapping("/{username}")
	public ResponseEntity<RestApiResponse> createCustomer(@Valid @RequestBody CustomerDto customer,@PathVariable String username){
		return new ResponseEntity<>(
				new RestApiResponse(true,customerService.createCustomer(customer,username)),HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Customer", description = "Update an existing customer's details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully update Customer")
    })
	@PutMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> updateCustomer(@Valid @RequestBody CustomerDto customer, @PathVariable Long customerId){
		return new ResponseEntity<>(
				new RestApiResponse(true,customerService.updateCustomer(customer,customerId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Delete Customer", description = "Delete a customer by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Delete Successfully")
    })
	@DeleteMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> deleteCustomer(@PathVariable Long customerId){
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(new RestApiResponse(true,"Customer Deleted successfully"),HttpStatus.OK);
	}
	
	@Operation(summary = "Get Customer Details", description = "Retrieve customer details by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer details retrieved successfully")
    })
	@GetMapping("/{customerId}")
	public ResponseEntity<RestApiResponse> getCustomerDetails(@PathVariable Long customerId){
		return new ResponseEntity<>(new RestApiResponse(true,customerService.findCustomer(customerId)),HttpStatus.OK);
	}
	
	
}
