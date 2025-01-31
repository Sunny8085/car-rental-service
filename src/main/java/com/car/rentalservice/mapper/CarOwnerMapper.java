package com.car.rentalservice.mapper;

import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.CarOwnerDto;
import com.car.rentalservice.model.CarOwner;

@Component
public class CarOwnerMapper {
	
	public CarOwner mappToNewCarOwner(CarOwnerDto carOwner) {
		CarOwner newCarOwner = new CarOwner();
		newCarOwner.setCarOwnerName(carOwner.getName());
		newCarOwner.setCarOwnerEmail(carOwner.getEmail());
		newCarOwner.setCarOwnerPhone(carOwner.getPhone());
		newCarOwner.setCarOwnerAddress(carOwner.getAddress());
		newCarOwner.setDriverLicense(carOwner.getDriverLicenseNumber());
		newCarOwner.setDateOfBirth(carOwner.getDateOfBirth());
		newCarOwner.setDistrict(carOwner.getDistrict());
		newCarOwner.setCountry(carOwner.getCountry());
		newCarOwner.setState(carOwner.getState());
		newCarOwner.setPincode(carOwner.getPincode());
		return newCarOwner;
	}
	
	public CarOwner mappToUpdateCarOwner(CarOwner updateCarOwner,CarOwnerDto carOwner) {
		updateCarOwner.setCarOwnerName(carOwner.getName());
		updateCarOwner.setCarOwnerEmail(carOwner.getEmail());
		updateCarOwner.setCarOwnerPhone(carOwner.getPhone());
		updateCarOwner.setCarOwnerAddress(carOwner.getAddress());
		updateCarOwner.setDateOfBirth(carOwner.getDateOfBirth());
		updateCarOwner.setDistrict(carOwner.getDistrict());
		updateCarOwner.setCountry(carOwner.getCountry());
		updateCarOwner.setState(carOwner.getState());
		updateCarOwner.setPincode(carOwner.getPincode());
		return updateCarOwner;
	}
	
}
