package com.car.rentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reservations_status")
public class ReservationsStatus {
	
	@Id
	@Column(name = "rev_status_code")
	private Long reservationsStatusCode;
	
	private String status;
	
	private String description;
	
}
