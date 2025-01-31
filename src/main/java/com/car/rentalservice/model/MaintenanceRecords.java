package com.car.rentalservice.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "maintenance_records")
public class MaintenanceRecords {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maintenance_id")
	private Long maintenanceId;
	
	@CreationTimestamp
	@Column(name = "service_date",updatable = false)
	private LocalDate serviceDate;
	
	private String description;
	
	private String cost;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "vehicleId", referencedColumnName = "vehicle_id", nullable = true)
	private Vehicles vehicle;
	
	
}
