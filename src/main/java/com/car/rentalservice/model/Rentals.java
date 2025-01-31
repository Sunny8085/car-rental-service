package com.car.rentalservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "rentals")
@SequenceGenerator(name = "rentals_seq", sequenceName = "rentals_SEQ", allocationSize = 1)
public class Rentals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rentals_seq")
	private Long rental_id;
	
	@OneToOne
	@JoinColumn(name = "reservation_id")
	private Reservations reservaton;
	
	@CreationTimestamp
	@Column(name = "rental_date",updatable = false)
	private LocalDateTime rentalDate;
	
	@Column(name = "return_date")
	private LocalDateTime returnDate;
	
	@Column(name = "total_amount")
	private String totalAmount;
	
	@ManyToOne(optional = true)
	@JoinColumn(name ="vehicle_id", nullable = true)
	private Vehicles vehicles;

	
}






