package com.car.rentalservice.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.locationtech.jts.geom.Geometry;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="vehicles")
public class Vehicles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vehicle_id")
	private Long id;
	
	@Column(name="make")
	private String manufacturer;
	
	private String model;
	
	private String year;
	
	private String locationName;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	@JsonIgnore
	@JsonProperty("carGeom")
    private Geometry carGeom;
	
	@Column(name="vin", updatable = false, unique = true)
	private String vehicleIdentNumber;
	
	@Column(name="license_plate")
	private String licensePlate;
	
	@Column(name="rental_rate", nullable = false)
	private String rentalRate;
	
	@CreationTimestamp
	@Column(name="created_at",updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "car_owner_id")
    private CarOwner carOwner;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_type_id", referencedColumnName = "vehicle_type_id")
	private VehicleTypes vehicleType;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_status_code" , referencedColumnName = "vehicle_status_code")
	private VehicleStatus vehicleStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private List<MaintenanceRecords> maintenanceRecords;
	
	@Builder
	public Vehicles(Long id, String manufacturer, String model, String year, String locationName, String address,
			String city, String state, String pincode, Geometry carGeom, String vehicleIdentNumber, String licensePlate,
			String rentalRate, LocalDateTime createdAt, LocalDateTime updatedAt, CarOwner carOwner,
			VehicleTypes vehicleType, VehicleStatus vehicleStatus, List<MaintenanceRecords> maintenanceRecords) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
		this.locationName = locationName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.carGeom = carGeom;
		this.vehicleIdentNumber = vehicleIdentNumber;
		this.licensePlate = licensePlate;
		this.rentalRate = rentalRate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.carOwner = carOwner;
		this.vehicleType = vehicleType;
		this.vehicleStatus = vehicleStatus;
		this.maintenanceRecords = maintenanceRecords;
	}
	
	
	
}




