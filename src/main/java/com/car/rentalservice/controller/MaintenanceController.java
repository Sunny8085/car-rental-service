package com.car.rentalservice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.service.MaintenanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/maintenance")
public class MaintenanceController {
	
	@Autowired private MaintenanceService maintenanceService;
	
	@Operation(summary = "Update Maintenance Staus Details", description = "Update vehicle maintenance status details.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/add-records/{vehicleId}")
	public ResponseEntity<RestApiResponse> updateMaintenance(
			@RequestParam Long vehicleId,
			@RequestParam LocalDate serviceDate,
			@RequestParam String description,
			@RequestParam String cost){
		return new ResponseEntity<>(new RestApiResponse
				(true, maintenanceService.addMaintenanceRecord(vehicleId, serviceDate, description, cost)),HttpStatus.OK);
	}
	
	@Operation(summary = "Get all Maintenance Details", description = "Get all maintenance details.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Maintenance record found")
	})
	@GetMapping("/get-records/{vehicleId}")
	public ResponseEntity<RestApiResponse> getMaintenanceRecord(@RequestParam Long vehicleId){
		return new ResponseEntity<>(new RestApiResponse
				(true, maintenanceService.getMaintenanceRecord(vehicleId)),HttpStatus.OK);
	}
	
}
