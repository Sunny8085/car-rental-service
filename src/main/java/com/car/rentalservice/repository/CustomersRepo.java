package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Customers;

public interface CustomersRepo extends JpaRepository<Customers, Long>{

}
