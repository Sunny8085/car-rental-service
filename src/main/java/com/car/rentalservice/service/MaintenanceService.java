package com.car.rentalservice.service;

import java.time.LocalDate;
import java.util.List;

import com.car.rentalservice.model.MaintenanceRecords;

public interface MaintenanceService {

	MaintenanceRecords addMaintenanceRecord(Long vehicleId, LocalDate serviceDate, String description, String cost);

	List<MaintenanceRecords> getMaintenanceRecord(Long vehicleId);

}
