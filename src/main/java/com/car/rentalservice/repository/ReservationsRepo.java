package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Reservations;

public interface ReservationsRepo extends JpaRepository<Reservations, Long>{

}
