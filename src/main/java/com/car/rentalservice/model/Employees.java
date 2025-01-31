package com.car.rentalservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employees {
	
	@Id
	@Column(name="employee_id")
	private Long employeeId;
	
	@Column(name="name",nullable = false)
	private String employeeName;
	
	@Column(name="email",nullable = false)
	private String employeeEmail;
	
	@Column(name="phone",nullable = false)
	private String employeePhone;
	
	@Column(nullable = false)
	private String position;
	
	@Column(name="driver_license_number",nullable = false, updatable = false)
	private String driverLicense;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="address")
	private String employeeAddress;

	private String district;
	
	private String country;
	
	private String state;
	
	private String pincode;
	
	@Column(name="hire_date",updatable = false)
	@CreationTimestamp
	private LocalDateTime hireDate;
	
	@Column(name="updated_at",insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToOne(mappedBy = "employee")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "status_code")
	private Status status;
	
}



