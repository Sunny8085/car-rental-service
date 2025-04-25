package com.car.rentalservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.RestApiResponse;
import com.car.rentalservice.dto.UsersDto;
import com.car.rentalservice.model.Users;
import com.car.rentalservice.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {
	
	private static final Logger logger = LogManager.getLogger(UsersController.class);
	@Autowired	private UsersService usersService;
   
    @Operation(summary = "Create a new user", description = "Create a new user")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created user")
    })
    @PostMapping("/register")
    public ResponseEntity<RestApiResponse> createUser(@Valid @RequestBody UsersDto user) {
    	Users newUser = usersService.createUser(user);
    	if (newUser != null)
    		return new ResponseEntity<>(new RestApiResponse(true,newUser),HttpStatus.CREATED);
    	else
    		return new ResponseEntity<>(new RestApiResponse(false,"Invalid input data"),HttpStatus.BAD_REQUEST);
    }
	
    @Operation(summary = "Login user", description = "Login user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully user login")
    })
    @PostMapping("/login")
    public ResponseEntity<RestApiResponse> login(@RequestBody UsersDto loginRequest){
    	Users user = usersService.findUser(loginRequest);
    	if(user != null)
    		return new ResponseEntity<>(new RestApiResponse(true, user),HttpStatus.OK);
    	else
    		return new ResponseEntity<>(new RestApiResponse(false,"User Id and password are not valid."),HttpStatus.NOT_FOUND);
    }
    
    @Operation(summary = "Genrate OTP for activate account", description = "Genrate OTP for activate account")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully Genrate OTP")
    })
    @GetMapping("/get-otp/{username}")
    public ResponseEntity<RestApiResponse> genrateOTP(@PathVariable String username){
    	return new ResponseEntity<>(new RestApiResponse(true,usersService.genrateOTP(username)),HttpStatus.OK);
    }
	
    @Operation(summary = "Verify OTP", description = "Verify OTP")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verify OTP Successfully")
    })
    @GetMapping("/verify-otp")
    public ResponseEntity<RestApiResponse> verifyOTP(@RequestParam String username,@RequestParam String OTP){
    	return new ResponseEntity<>(new RestApiResponse(true,usersService.verifyOTP(username,OTP)),HttpStatus.OK);
    }
    
}















