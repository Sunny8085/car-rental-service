package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.VehicleStatus;

public interface VehicleStatusRepo extends JpaRepository<VehicleStatus, String>{

}
