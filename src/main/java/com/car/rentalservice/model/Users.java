package com.car.rentalservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class Users {
	
	@Id
	@Column(name="username")
	private String userName;
	
	@Column(unique = true,nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String password;
	
	@Column(unique = true, nullable = false, name = "mobile_no")
	private String mobileNo;
	
	@ManyToOne
	@JoinColumn(name ="role_id" ,referencedColumnName = "role_id")
	private Roles roles;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customers customer;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_owner_id", nullable = true)
    private CarOwner carOwner;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id", nullable = true)
	private Employees employee;
	
	@CreationTimestamp
	@Column(name="created_at",updatable = false)
	private LocalDateTime createdAt;
	
	@CreationTimestamp
	@Column(name="login_stamp")
	private LocalDateTime loginStamp;
	
	private String otp;
	
	@Column(name="otp_time")
	private LocalDateTime otpTime;

	
}
