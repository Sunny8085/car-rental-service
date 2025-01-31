package com.car.rentalservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.MaintenanceRecords;

public interface MaintenanceRecordsRepo extends JpaRepository<MaintenanceRecords, Long>{

	List<MaintenanceRecords> findByVehicle_Id(Long vehicleId);

}
