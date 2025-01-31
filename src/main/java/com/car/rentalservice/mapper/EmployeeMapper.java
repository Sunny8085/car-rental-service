package com.car.rentalservice.mapper;

import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.CustomerDto;
import com.car.rentalservice.dto.EmployeeDto;
import com.car.rentalservice.model.Customers;
import com.car.rentalservice.model.Employees;

import jakarta.validation.Valid;

@Component
public class EmployeeMapper {
	
	public Employees mappToNewEmployee(EmployeeDto employee) {
		Employees newEmployee = new Employees();
		newEmployee.setEmployeeName(employee.getName());
		newEmployee.setEmployeeEmail(employee.getEmail());
		newEmployee.setEmployeePhone(employee.getPhone());
		newEmployee.setPosition(employee.getPosition());
		newEmployee.setDriverLicense(employee.getDriverLicense());
		newEmployee.setDateOfBirth(employee.getDateOfBirth());
		newEmployee.setEmployeeAddress(employee.getEmployeeAddress());
		newEmployee.setDistrict(employee.getDistrict());
		newEmployee.setCountry(employee.getCountry());
		newEmployee.setState(employee.getState());
		newEmployee.setPincode(employee.getPincode());
		return newEmployee;
	}
	
	public Customers mappToNewCoustomer(CustomerDto customer) {
		Customers newCustomer = new Customers();
		newCustomer.setCustomerName(customer.getName());
		newCustomer.setCustomerEmail(customer.getEmail());
		newCustomer.setCustomerPhone(customer.getPhone());
		newCustomer.setCustomerAddress(customer.getAddress());
		newCustomer.setDriverLicense(customer.getDriverLicenseNumber());
		newCustomer.setDateOfBirth(customer.getDateOfBirth());
		newCustomer.setDistrict(customer.getDistrict());
		newCustomer.setCountry(customer.getCountry());
		newCustomer.setState(customer.getState());
		newCustomer.setPincode(customer.getPincode());
		return newCustomer;
	}

	public Employees mappToUpdateCoustomer(Employees updateEmployee, @Valid EmployeeDto employee) {
		Employees newEmployee = new Employees();
		newEmployee.setEmployeeName(employee.getName());
		newEmployee.setEmployeeEmail(employee.getEmail());
		newEmployee.setEmployeePhone(employee.getPhone());
		newEmployee.setPosition(employee.getPosition());
		newEmployee.setDriverLicense(employee.getDriverLicense());
		newEmployee.setDateOfBirth(employee.getDateOfBirth());
		newEmployee.setEmployeeAddress(employee.getEmployeeAddress());
		newEmployee.setDistrict(employee.getDistrict());
		newEmployee.setCountry(employee.getCountry());
		newEmployee.setState(employee.getState());
		newEmployee.setPincode(employee.getPincode());
		return newEmployee;
	}
	
}
