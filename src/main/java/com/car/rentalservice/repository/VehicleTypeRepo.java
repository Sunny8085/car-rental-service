package com.car.rentalservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.VehicleTypes;

public interface VehicleTypeRepo extends JpaRepository<VehicleTypes, Long>{

	Optional<VehicleTypes> findByTypeName(String vehicleType);

}
