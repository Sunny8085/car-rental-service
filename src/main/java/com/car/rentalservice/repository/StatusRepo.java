package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Status;

public interface StatusRepo extends JpaRepository<Status, String>{

}
