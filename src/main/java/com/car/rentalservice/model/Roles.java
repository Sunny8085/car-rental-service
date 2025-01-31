package com.car.rentalservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Roles {
	
	@Id
	@Column(name="role_id")
	private String roleId;
	
	@Column(name = "role_name")
	private String roleName;
	
}
