package com.car.rentalservice.controller;

import java.util.List;

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

import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.dto.VehiclesDto;
import com.car.rentalservice.model.Vehicles;
import com.car.rentalservice.service.VehiclesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehiclesController {
	
	@Autowired
	private VehiclesService vehicleService;
	
	@Operation(summary = "Get Vehicle Details", description = "Retrieve vehicle details by its ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Vehicles details retrieved successfully")
	})
	@GetMapping("/{vehicleId}")
	public ResponseEntity<RestApiResponse> getVehicles(@PathVariable Long vehicleId){
		Vehicles vehicle = vehicleService.getvehicleById(vehicleId);
		if(vehicle != null)
			return new ResponseEntity<>(new RestApiResponse(true, vehicle),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestApiResponse(false, "Vehicle Not Found"),HttpStatus.OK);
	}
	
	@Operation(summary = "Get Vehicle Details", description = "Retrieve vehicle details by its Type")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicles details retrieved successfully")
	})
	@GetMapping("/vehicle-type/{vehicleTypeId}")
	public ResponseEntity<RestApiResponse> getVehiclesByType(@PathVariable Long vehicleTypeId){
		List<Vehicles> vehicles = vehicleService.getVehiclesByType(vehicleTypeId);
		if(vehicles != null && !vehicles.isEmpty())
			return new ResponseEntity<>(new RestApiResponse(true, vehicles),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestApiResponse(false, "Vehicle Not Found"),HttpStatus.OK);
	}
	
	@Operation(summary = "Delete Vehicle", description = "Delete vehicle details by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Delete  successfully")
	})
	@DeleteMapping("/{vehicleId}")
	public ResponseEntity<RestApiResponse> deleteVehicles(@PathVariable Long vehicleId){
		vehicleService.deleteVehicleById(vehicleId);
		return new ResponseEntity<>(new RestApiResponse(true, "Vehicle deleted successfully"),HttpStatus.OK);
	}
	
	@Operation(summary = "Add Vehicle", description = "Add new vehicle details")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Vehicle added  successfully")
	})
	@PostMapping("/{carOwnerId}")
	public ResponseEntity<RestApiResponse> addVehicles(@RequestBody VehiclesDto vehicle, @PathVariable Long carOwnerId){
		Vehicles newVehicle = vehicleService.addVehicles(vehicle,carOwnerId);
		return new ResponseEntity<>(new RestApiResponse(true, newVehicle), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Update Vehicle Details", description = "Update vehicle details by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/{vehicleId}")
	public ResponseEntity<RestApiResponse> updateVehicles(@RequestBody VehiclesDto vehicle, @PathVariable Long vehicleId){
		return new ResponseEntity<>(new RestApiResponse
				(true, vehicleService.updateVehicleById(vehicle, vehicleId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Get Vehicle Status Details", description = "Retrieve vehicle status details")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicles status details retrieved successfully")
	})
	@GetMapping("/vehicle-status")
	public ResponseEntity<RestApiResponse> getVehiclesStatus(){
		return new ResponseEntity<>(new RestApiResponse(true, vehicleService.getVehicleStatus()),HttpStatus.OK);
	}
	
	@Operation(summary = "Update Vehicle Staus Details", description = "Update vehicle status details by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/vehicle-status/{vehicleId}")
	public ResponseEntity<RestApiResponse> updateVehicleStatus(@RequestParam String vehicleStatus, @PathVariable Long vehicleId){
		return new ResponseEntity<>(new RestApiResponse
				(true, vehicleService.updateVehicleStatus(vehicleStatus, vehicleId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Get Vehicle list", description = "Get all vehicle list by it's cordinate")
	@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully found the shortest path")
	})
	@GetMapping("/vehicle-list")
	public ResponseEntity<RestApiResponse> getVehicleList(
			@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam(defaultValue = "0") int pageOffset,
			@RequestParam(defaultValue = "5") int pageSize){
		return new ResponseEntity<>(new RestApiResponse(true, vehicleService.getClosestVehicle(latitude,longitude,pageOffset,pageSize)),HttpStatus.OK);
	}
	
}






