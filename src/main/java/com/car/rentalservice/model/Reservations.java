package com.car.rentalservice.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Data
@Entity
@Table(name ="reservations")
@SequenceGenerator(name = "reservation_seq", sequenceName = "reservations_SEQ", allocationSize = 1)
public class Reservations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
	@Column(name="reservation_id")
	private Long  reservationId;
	
	@CreationTimestamp
	@Column(name="reservation_date",updatable = false)
	private LocalDateTime reservationDate;
	
	@Column(name="pickup_date")
	private LocalDateTime pickupDate;
	
	@Column(name ="dropoff_date")
	private LocalDateTime dropoffDate;
	
	@Column(name = "rev_otp")
	private String reservationsOTP;
	
	@Column(name = "rev_otp_time")
	private LocalDateTime reservationsOTPTime;
	
	private String amount;
	
	private String paymentStatus;
	
	private String responseTransactionId;
	
	private String refrenceTransactionId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="customer_id")
	private Customers customer;
	
	@ManyToOne
	@JoinColumn(name = "rev_status_code" , referencedColumnName = "rev_status_code")
	private ReservationsStatus reservationsStatus;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = true)
	private Vehicles vehicle; 
	
	@OneToOne(optional = true)
    @JoinColumn(name = "pickup_location_id", referencedColumnName = "location_id")
    private Location pickupLocation;

    @OneToOne(optional = true)
    @JoinColumn(name = "dropoff_location_id", referencedColumnName = "location_id")
    private Location dropoffLocation;

    
}
