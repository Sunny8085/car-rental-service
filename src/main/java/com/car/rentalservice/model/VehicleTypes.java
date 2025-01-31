package com.car.rentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="vehicle_types")
public class VehicleTypes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicle_type_id")
	private Long vehicleTypeId;
	
	@Column(name="type_name")
	private String typeName;
	
	@Column(name="description")
	private String description;
	
}
