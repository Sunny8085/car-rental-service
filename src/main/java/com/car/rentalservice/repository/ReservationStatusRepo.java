package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.ReservationsStatus;

public interface ReservationStatusRepo extends JpaRepository<ReservationsStatus, Long>{

}
