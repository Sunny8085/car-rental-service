package com.car.rentalservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.EmployeeDto;
import com.car.rentalservice.emums.CustomerStatus;
import com.car.rentalservice.exception.ConflictException;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.mapper.EmployeeMapper;
import com.car.rentalservice.model.CarOwner;
import com.car.rentalservice.model.Customers;
import com.car.rentalservice.model.Employees;
import com.car.rentalservice.model.Status;
import com.car.rentalservice.model.Users;
import com.car.rentalservice.model.VehicleStatus;
import com.car.rentalservice.model.Vehicles;
import com.car.rentalservice.repository.CarOwnerRepo;
import com.car.rentalservice.repository.CustomersRepo;
import com.car.rentalservice.repository.EmployeesRepo;
import com.car.rentalservice.repository.StatusRepo;
import com.car.rentalservice.repository.UsersRepo;
import com.car.rentalservice.repository.VehicleStatusRepo;
import com.car.rentalservice.repository.VehiclesRepo;
import com.car.rentalservice.service.EmployeesService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EmployeesServiceImpl implements EmployeesService {
	
	@Autowired private EmployeesRepo employeeRepo;
	@Autowired private UsersRepo userRepo;
	@Autowired private StatusRepo statusRepo;
	@Autowired private VehicleStatusRepo vehicleStatusRepo;
	@Autowired private EmployeeMapper employeeMapper;
	@Autowired private VehiclesRepo vehicleRepo;
	@Autowired private CarOwnerRepo carOwnerRepo;
	@Autowired private CustomersRepo customerRepo;

	@Override
	@Transactional
	public Employees createEmployee(@Valid EmployeeDto employee, String username) {
		Users user = userRepo.findById(username)
				.orElseThrow(() -> new NotFoundException("Users not found."));
		if(user.getEmployee() != null)
			throw new ConflictException("Employee already exists.");
		else if(user.getOtp() == null || !user.getOtp().equals("1"))
			throw new ConflictException("OTP not verified");
		
		Employees newEmployee = employeeMapper.mappToNewEmployee(employee);
		Status status = statusRepo.findById(CustomerStatus.Active.getCode())
				.orElseThrow(() -> new NotFoundException("Employee status not found"));
		newEmployee.setStatus(status);
		Employees savedEmployee = employeeRepo.save(newEmployee);
		
		user.setEmployee(savedEmployee);
		userRepo.save(user);
		
		return savedEmployee;
	}

	@Override
	public Employees updateEmployee(@Valid EmployeeDto employee, Long employeeId) {
		Employees updateEmployee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new NotFoundException("Employee not found"));
		return employeeRepo.save(employeeMapper.mappToUpdateCoustomer(updateEmployee,employee));
	}
	
	@Override
	@Transactional
	public void deleteEmployee(Long employeeId) {
		employeeRepo.findById(employeeId)
					.orElseThrow(() -> new NotFoundException("Employee not found."));
		if(userRepo.updateEmployeeIdToNull(employeeId) == null)
			employeeRepo.deleteById(employeeId);
	}

	@Override
	public Employees findEmployee(Long employeeId) {
		return employeeRepo.findById(employeeId)
				.orElseThrow(() -> new NotFoundException("Employee not found."));
	}

	@Override
	public CarOwner updateCarStatus(String statusCode, Long carOwnerId) {
		Status carOwnerStatus = statusRepo.findById(statusCode)
				.orElseThrow(() -> new NotFoundException("Status not found"));
		CarOwner carOwner = carOwnerRepo.findById(carOwnerId)
				.orElseThrow(() -> new NotFoundException("Car owner not found"));
		carOwner.setStatus(carOwnerStatus);
		return carOwnerRepo.save(carOwner);
	}

	@Override
	public Customers updateCustomerStatus(String statusCode, Long customerId) {
		Status customerStatus = statusRepo.findById(statusCode)
				.orElseThrow(() -> new NotFoundException("Status not found"));
		Customers customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer NOt found"));
		customer.setStatus(customerStatus);
		return customerRepo.save(customer);
	}

	@Override
	public Vehicles updateVehicleStatus(String statusCode, Long vehicleId) {
		VehicleStatus vehicelStatus = vehicleStatusRepo.findById(statusCode)
				.orElseThrow(() -> new NotFoundException(statusCode));
		Vehicles vehicle = vehicleRepo.findById(vehicleId)
				.orElseThrow(() -> new NotFoundException("Vehicle not found."));
		vehicle.setVehicleStatus(vehicelStatus);
		return vehicleRepo.save(vehicle);
	}
	
}




