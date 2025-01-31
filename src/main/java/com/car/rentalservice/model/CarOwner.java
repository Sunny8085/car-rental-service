package com.car.rentalservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="car_owner")
public class CarOwner {
	
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="car_owner_id")
	private Long carOwnerId;
	
	@Column(name="car_owner")
	private String carOwnerName;
	
	@Column(name="email")
	private String carOwnerEmail;
	
	@Column(name="phone")
	private String carOwnerPhone;
	
	@Column(name="driver_license_number",nullable = false, updatable = false)
	private String driverLicense;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="address")
	private String carOwnerAddress;

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
	
	@JsonIgnore
	@OneToMany(mappedBy = "carOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehicles> vehicles;

	@ManyToOne
	@JoinColumn(name = "status_code" , referencedColumnName = "status_code")
	private Status status;

	
}



