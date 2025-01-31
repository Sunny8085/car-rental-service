package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.rentalservice.model.Users;

public interface UsersRepo extends JpaRepository<Users, String>{

	Users findByUserNameAndPassword(String username, String password);
	
	@Modifying
	@Query(value = "update users set otp = '1' WHERE username = :username", nativeQuery = true)
	int updateOTPStaus(@Param("username") String username);
	
	@Query(value = "UPDATE users SET customer_id = NULL WHERE customer_id = :customerId RETURNING customer_id",
			nativeQuery = true)
	String updatecustomerIdToNull(Long customerId);
	
	@Query(value = "UPDATE users SET car_owner_id = NULL WHERE car_owner_id = :carOwnerId RETURNING car_owner_id",
			nativeQuery = true)
	String updateCarOwnerIdToNull(Long carOwnerId);
	
	@Query(value = "UPDATE users SET employee_id = NULL WHERE employee_id = :employeeId RETURNING employee_id",
			nativeQuery = true)
	String updateEmployeeIdToNull(Long employeeId);

}
