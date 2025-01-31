package com.car.rentalservice.mapper;

import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.UsersDto;
import com.car.rentalservice.model.Users;

@Component
public class UserMapper {

	public Users mappToNewUser(UsersDto user) {
		Users newUser = new Users();
		newUser.setUserName(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setMobileNo(user.getMobileNo());
		return newUser;	
	}
	
}




