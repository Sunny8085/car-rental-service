package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Employees;

public interface EmployeesRepo extends JpaRepository<Employees, Long>{

}
