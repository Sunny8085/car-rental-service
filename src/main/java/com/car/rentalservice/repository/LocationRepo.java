package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Location;

public interface LocationRepo extends JpaRepository<Location, Long>{

}
