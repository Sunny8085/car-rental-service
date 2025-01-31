package com.car.rentalservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.LocationDto;
import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.model.Reservations;
import com.car.rentalservice.service.ReservationsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationsController {
	
	@Autowired
	private ReservationsService reservationService;
	
	@Operation(summary = "Get Reservation Details", description = "Retrieve reservation details by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Reservation details retrieved successfully")
	})
	@GetMapping("/{reservationId}")
	public ResponseEntity<RestApiResponse> getReservation(@PathVariable Long reservationId){
		Reservations reservation = reservationService.getReservaitonById(reservationId);
		if(reservation != null)
			return new ResponseEntity<>(new RestApiResponse(true, reservation),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestApiResponse(false, "Reservation Not Found"),HttpStatus.OK);
	}
	
	@Operation(summary = "New Reservation", description = "Add new reservation details")
	@ApiResponses({
	    @ApiResponse(responseCode = "201", description = "Added  successfully")
	})
	@PostMapping("/")
	public ResponseEntity<RestApiResponse> addReservation(@RequestParam Long vehicleId, @RequestParam Long customerId){
		Reservations newReservation = reservationService.addReservation(vehicleId, customerId);
		return new ResponseEntity<>(new RestApiResponse(true, newReservation), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Reservation otp verify", description = "Verify reservation otp by its ID")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "OTP is correct")
	})
	@GetMapping("/otp-verify")
	public ResponseEntity<RestApiResponse> verifyOTP(@RequestParam String otp, @RequestParam Long reservationId ){
		Boolean status = reservationService.verfiyOTP(reservationId,otp);
		if(status)
			return new ResponseEntity<>(new RestApiResponse(true, "OTP verify successfully"),HttpStatus.OK);
		else
			return new ResponseEntity<>(new RestApiResponse(false, "Wrong otp"),HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Reservation otp genration", description = "Genrate reservation otp by its ID")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "OTP is correct")
	})
	@GetMapping("/resend-otp")
	public ResponseEntity<RestApiResponse> sendOTP(@RequestParam Long reservationId){
		return new ResponseEntity<>(new RestApiResponse(true, reservationService.sendOTP(reservationId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update reservation Details", description = "Update pickup location by its ID")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/pickup-location/{reservationId}")
	public ResponseEntity<RestApiResponse> addPickup(@RequestBody LocationDto pickupLocation, @PathVariable Long reservationId){
		return new ResponseEntity<>(
				new RestApiResponse(true, reservationService.addPickupLocation(pickupLocation, reservationId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update reservation Details", description = "Update dropoff location by its ID")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/dropoff-location/{reservationId}")
	public ResponseEntity<RestApiResponse> addDropoff(@RequestBody LocationDto dropLocation, @PathVariable Long reservationId){
		return new ResponseEntity<>(
				new RestApiResponse(true, reservationService.addDropoffLocation(dropLocation, reservationId)),HttpStatus.OK);
	}
	
	@Operation(summary = "Update payment Details", description = "Update payment details by its reservaiton ID")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Update  successfully")
	})
	@PutMapping("/payment")
	public ResponseEntity<RestApiResponse> addDropoff(
			@RequestParam Long reservationId,
			@RequestParam String responseTransactionId,
			@RequestParam String refrenceTransactionId,
			@RequestParam String amount){
		return new ResponseEntity<>(
				new RestApiResponse(true, reservationService.updatePaymentDetails(reservationId, responseTransactionId, refrenceTransactionId, amount)),HttpStatus.OK);
	}
	
}
