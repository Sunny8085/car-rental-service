package com.car.rentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
@SequenceGenerator(name = "reservation_seq", sequenceName = "location_SEQ", allocationSize = 1)
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
	@Column(name = "location_id")
	private Long locationId;
	
	private String locationName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	private String locationType;
	
}
