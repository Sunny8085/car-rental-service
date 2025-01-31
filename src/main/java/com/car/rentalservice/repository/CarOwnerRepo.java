package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.CarOwner;

public interface CarOwnerRepo extends JpaRepository<CarOwner, Long>{

}
