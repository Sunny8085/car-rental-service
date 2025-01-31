package com.car.rentalservice.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.VehiclesDto;
import com.car.rentalservice.emums.VehicleStatusCode;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.mapper.VehiclesMapper;
import com.car.rentalservice.model.CarOwner;
import com.car.rentalservice.model.VehicleStatus;
import com.car.rentalservice.model.VehicleTypes;
import com.car.rentalservice.model.Vehicles;
import com.car.rentalservice.repository.CarOwnerRepo;
import com.car.rentalservice.repository.VehicleStatusRepo;
import com.car.rentalservice.repository.VehicleTypeRepo;
import com.car.rentalservice.repository.VehiclesRepo;
import com.car.rentalservice.service.VehiclesService;
import com.car.rentalservice.util.GeometryUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class VehiclesServiceImpl implements VehiclesService {
	
	@Autowired private VehiclesRepo vehicleRepo;
	@Autowired private VehicleTypeRepo vehicleTypeRepo;
	@Autowired private VehiclesMapper vehiclesMapper;
	@Autowired private VehicleStatusRepo vehicleStatusRepo;
	@Autowired private CarOwnerRepo carOwnerRepo;
	@Autowired private RedisServiceImpl redisService;

	@Override
	public Vehicles addVehicles(VehiclesDto vehicle, Long carOwnerId) {
		CarOwner owner = carOwnerRepo.findById(carOwnerId)
					.orElseThrow(() -> new NotFoundException("Car owner not found"));
		
		Vehicles newVehicle = vehiclesMapper.mapToNewVehicle(vehicle);
		VehicleStatus availableStatus = vehicleStatusRepo.findById(VehicleStatusCode.AVAILABLE.getCode())
			    .orElseThrow(() -> new NotFoundException("Vehicle status 'AVAILABLE' not found"));
		newVehicle.setVehicleStatus(availableStatus);
		
		VehicleTypes vehicleType = vehicleTypeRepo.findByTypeName(vehicle.getVehicleType())
				.orElseThrow(() -> new NotFoundException("Vehicle type not found"));
		newVehicle.setVehicleType(vehicleType);
		
		newVehicle.setCarOwner(owner);
		newVehicle.setManufacturer(vehicle.getManufacturer());
		newVehicle.setModel(vehicle.getModel());
		newVehicle.setYear(vehicle.getYear());
		newVehicle.setVehicleIdentNumber(vehicle.getVehicleIdentNumber());
		newVehicle.setLicensePlate(vehicle.getLicensePlate());
		newVehicle.setRentalRate(vehicle.getRentalRate());
		newVehicle.setCarGeom(GeometryUtil.stringTOGeometry(vehicle.getCarGeom()));
		
		return vehicleRepo.save(newVehicle);
	}

	@Override
	public Vehicles getvehicleById(Long vehicleId) {
		return vehicleRepo.findById(vehicleId).orElse(null);
	}

	@Override
	public void deleteVehicleById(Long vehicleId) {
		vehicleRepo.findById(vehicleId).orElseThrow(() -> new NotFoundException("Vehicel not found"));
		vehicleRepo.deleteById(vehicleId);
	}

	@Override
	public List<Vehicles> getVehiclesByType(Long vehicleTypeId) {
		return vehicleRepo.findByVehicleType_VehicleTypeId(vehicleTypeId);
	}

	@Override
	public Vehicles updateVehicleById(VehiclesDto vehicle, Long vehicleId) {
		Vehicles upateVehicle = vehicleRepo.findById(vehicleId)
					.orElseThrow(() -> new NotFoundException("Vehicle not found."));
		
		upateVehicle.setManufacturer(vehicle.getManufacturer());
		upateVehicle.setModel(vehicle.getModel());
		upateVehicle.setYear(vehicle.getYear());
		upateVehicle.setVehicleIdentNumber(vehicle.getVehicleIdentNumber());
		upateVehicle.setLicensePlate(vehicle.getLicensePlate());
		upateVehicle.setRentalRate(vehicle.getRentalRate());
		return vehicleRepo.save(upateVehicle);
	}

	@Override
	public List<VehicleStatus> getVehicleStatus() {
		return vehicleStatusRepo.findAll();
	}

	@Override
	public Vehicles updateVehicleStatus(String vehicleStatus, Long vehicleId) {
		Vehicles vehicle = vehicleRepo.findById(vehicleId)
				   .orElseThrow(() -> new NotFoundException("Vehicle not found"));
		
		VehicleStatus status = vehicleStatusRepo.findById(vehicleStatus)
				   .orElseThrow(() -> new NotFoundException("Vehicle status not found"));
		vehicle.setVehicleStatus(status);
		
		return vehicleRepo.save(vehicle);
	}

	@Override
	public List<Vehicles> getClosestVehicle(double latitude, double longitude,int pageOffset,int pageSize) {
		String key = "customer_location"+latitude+longitude+pageOffset+pageSize;
		List<Vehicles> vehicles = redisService.getClosestVehicle(key, new TypeReference<List<Vehicles>>() {});
		if(vehicles == null) {
			List<Object[]> closestVehicle = vehicleRepo.findClosestVehicle(latitude,longitude,pageOffset,pageSize);
			vehicles = closestVehicle.stream().map(v -> 
				Vehicles.builder().id((Long)v[0])
				.manufacturer((String)v[1])
				.model((String)v[2])
				.year((String)v[3])
				.vehicleIdentNumber((String)v[4])
				.licensePlate((String)v[5])
				.rentalRate((String)v[6])
				.address((String)v[11])
				.city((String)v[12])
				.locationName((String)v[13])
				.pincode((String)v[14])
				.state((String)v[15])
				.build()).collect(Collectors.toList());
			redisService.set(key, vehicles, 60L);
		}
		return vehicles;
	}

	
	
}





