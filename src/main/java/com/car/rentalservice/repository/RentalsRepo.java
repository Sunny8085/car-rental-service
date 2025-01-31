package com.car.rentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rentalservice.model.Rentals;

public interface RentalsRepo extends JpaRepository<Rentals, Long>{

	Rentals findByReservatonReservationId(Long reservationId);

}
