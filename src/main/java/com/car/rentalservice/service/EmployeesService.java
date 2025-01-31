package com.car.rentalservice.service;

import com.car.rentalservice.dto.EmployeeDto;
import com.car.rentalservice.model.CarOwner;
import com.car.rentalservice.model.Customers;
import com.car.rentalservice.model.Employees;
import com.car.rentalservice.model.Vehicles;

import jakarta.validation.Valid;

public interface EmployeesService {

	Employees createEmployee(@Valid EmployeeDto employee, String username);

	Employees updateEmployee(@Valid EmployeeDto employee, Long employeeId);

	void deleteEmployee(Long employeeId);

	Employees findEmployee(Long employeeId);

	CarOwner updateCarStatus(String statusCode, Long carOwnerId);

	Customers updateCustomerStatus(String statusCode, Long customerId);

	Vehicles updateVehicleStatus(String statusCode, Long vehicleId);

}
