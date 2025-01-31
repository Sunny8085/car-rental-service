package com.car.rentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicles_status")
public class VehicleStatus {
	
	@Id
	@Column(name = "vehicle_status_code")
	private String vehicleStatusCode;
	
	@Column(name = "status_desc")
	private String statusDesc;
	
}
