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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.EmployeeDto;
import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.service.EmployeesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeeService;
	
	@Operation(summary = "Create a new employee", description = "Create a new employee")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created employee")
    })
	@PostMapping("/{username}")
	public ResponseEntity<RestApiResponse> createEmployee(@Valid @RequestBody EmployeeDto employee,@PathVariable String username){
		return new ResponseEntity<>(
				new RestApiResponse(true,employeeService.createEmployee(employee,username)),HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update employee", description = "Update an existing employee's details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully update employee")
    })
	@PutMapping("/{employeeId}")
	public ResponseEntity<RestApiResponse> updateemployee(@Valid @RequestBody EmployeeDto employee, @PathVariable Long employeeId){
		return new ResponseEntity<>(
				new RestApiResponse(true,employeeService.updateEmployee(employee,employeeId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Delete employee", description = "Delete a employee by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Delete Successfully")
    })
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<RestApiResponse> deleteemployee(@PathVariable Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>(new RestApiResponse(true,"employee Deleted successfully"),HttpStatus.OK);
	}
	
	@Operation(summary = "Get employee Details", description = "Retrieve employee details by their ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee details retrieved successfully")
    })
	@GetMapping("/{employeeId}")
	public ResponseEntity<RestApiResponse> getemployeeDetails(@PathVariable Long employeeId){
		return new ResponseEntity<>(new RestApiResponse(true,employeeService.findEmployee(employeeId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update Car Owner", description = "Update car owner status")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status update successfully")
    })
	@PutMapping("/car-owner-status")
	public ResponseEntity<RestApiResponse> changeCarOwnerStatus(@RequestParam String statusCode, @RequestParam Long carOwnerId){
		return new ResponseEntity<>(
				new RestApiResponse(true, employeeService.updateCarStatus(statusCode,carOwnerId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update Customer", description = "Update customer staus")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status update successfully")
    })
	@PutMapping("/customer-status")
	public ResponseEntity<RestApiResponse> changeCustomerStatus(@RequestParam String statusCode, @RequestParam Long customerId){
		return new ResponseEntity<>(
				new RestApiResponse(true, employeeService.updateCustomerStatus(statusCode,customerId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update employee", description = "Update an existing employee's details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status update successfully")
    })
	@PutMapping("/vehicle-status")
	public ResponseEntity<RestApiResponse> changeVehicleStatus(@RequestParam String statusCode, @RequestParam Long vehicleId){
		return new ResponseEntity<>(
				new RestApiResponse(true, employeeService.updateVehicleStatus(statusCode,vehicleId)),HttpStatus.OK);
	}
	
	
}







