package com.car.rentalservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="customers")
public class Customers {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="name")
	private String customerName;
	
	@Column(name="email")
	private String customerEmail;
	
	@Column(name="phone")
	private String customerPhone;
	
	@Column(name="driver_license_number",nullable = false, updatable = false)
	private String driverLicense;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="address")
	private String customerAddress;

	private String district;
	
	private String country;
	
	private String state;
	
	private String pincode;
	
	@CreationTimestamp
	@Column(name="created_at",updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at" , insertable = false)
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "status_code" , referencedColumnName = "status_code")
	private Status status;
	
	@OneToMany(mappedBy = "customer")
	private List<Reservations> reservation;
	
}



