package com.car.rentalservice.service;

import com.car.rentalservice.dto.UsersDto;
import com.car.rentalservice.model.Users;

public interface UsersService {

	Users createUser(UsersDto user);

	Users findUser(UsersDto loginRequest);

	String genrateOTP(String username);

	String verifyOTP(String username, String oTP);

}
