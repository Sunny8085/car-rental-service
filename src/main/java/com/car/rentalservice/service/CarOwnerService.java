package com.car.rentalservice.service;

import com.car.rentalservice.dto.CarOwnerDto;
import com.car.rentalservice.model.CarOwner;

public interface CarOwnerService {

	CarOwner createOwner(CarOwnerDto owner, String username);

	CarOwner updateOwner(CarOwnerDto owner, Long customerId);

	void deleteOwner(Long customerId);

	CarOwner findOwner(Long customerId);

}
