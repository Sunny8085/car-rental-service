package com.car.rentalservice.mapper;

import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.VehiclesDto;
import com.car.rentalservice.model.Vehicles;

@Component
public class VehiclesMapper {

	public Vehicles mapToNewVehicle(VehiclesDto vehicle) {
		Vehicles newVehicle = new Vehicles();
		newVehicle.setManufacturer(vehicle.getManufacturer());
		newVehicle.setModel(vehicle.getModel());
		newVehicle.setYear(vehicle.getYear());
		newVehicle.setVehicleIdentNumber(vehicle.getVehicleIdentNumber());
		newVehicle.setLicensePlate(vehicle.getLicensePlate());
		newVehicle.setRentalRate(vehicle.getRentalRate());
		newVehicle.setLocationName(vehicle.getLocationName());
		newVehicle.setAddress(vehicle.getAddress());
		newVehicle.setCity(vehicle.getCity());
		newVehicle.setState(vehicle.getState());
		newVehicle.setPincode(vehicle.getPincode());
		return newVehicle;
	}

}
