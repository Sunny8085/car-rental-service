package com.car.rentalservice.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.model.MaintenanceRecords;
import com.car.rentalservice.model.Vehicles;
import com.car.rentalservice.repository.MaintenanceRecordsRepo;
import com.car.rentalservice.repository.VehiclesRepo;
import com.car.rentalservice.service.MaintenanceService;

@Service
public class MaintenanceImpl implements MaintenanceService{
	
	@Autowired private MaintenanceRecordsRepo maintenanceRecordsRepo;
	@Autowired private VehiclesRepo vehiclesRepo;
	
	@Override
	public MaintenanceRecords addMaintenanceRecord(Long vehicleId, LocalDate serviceDate, String description,
			String cost) {
		Vehicles vehicle = vehiclesRepo.findById(vehicleId)
				.orElseThrow(() -> new NotFoundException("vehicle not found."));
		MaintenanceRecords maintenance = new MaintenanceRecords();
		maintenance.setDescription(description);
		maintenance.setCost(cost);
		maintenance.setServiceDate(serviceDate);
		maintenance.setVehicle(vehicle);
		return maintenanceRecordsRepo.save(maintenance);
	}

	@Override
	public List<MaintenanceRecords> getMaintenanceRecord(Long vehicleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
