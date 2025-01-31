package com.car.rentalservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="status")
@Data
public class Status {
	
	@Id
	private String status_code;
	
	private String status_desc;
	
}
