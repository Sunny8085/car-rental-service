package com.car.rentalservice.service;

import java.util.List;

import com.car.rentalservice.dto.VehiclesDto;
import com.car.rentalservice.model.VehicleStatus;
import com.car.rentalservice.model.Vehicles;

public interface VehiclesService {

	Vehicles addVehicles(VehiclesDto vehicle,Long carOwnerId);

	Vehicles getvehicleById(Long vehicleId);

	void deleteVehicleById(Long vehicleId);

	List<Vehicles> getVehiclesByType(Long vehicleTypeId);

	Vehicles updateVehicleById(VehiclesDto vehicle, Long vehicleId);

	List<VehicleStatus> getVehicleStatus();

	Vehicles updateVehicleStatus(String vehicleStatus, Long vehicleId);

	List<Vehicles> getClosestVehicle(double latitude, double longitude,int pageOffset,int pageSize);


}
