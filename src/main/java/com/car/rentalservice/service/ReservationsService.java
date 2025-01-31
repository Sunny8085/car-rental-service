package com.car.rentalservice.service;

import com.car.rentalservice.dto.LocationDto;
import com.car.rentalservice.model.Reservations;

public interface ReservationsService {

	Reservations getReservaitonById(Long reservationId);

	Reservations addReservation(Long vehicleId, Long customerId);

	Boolean verfiyOTP(Long reservationId, String otp);

	String sendOTP(Long reservationId);

	Reservations addPickupLocation(LocationDto pickupLocation, Long reservationId);

	Reservations addDropoffLocation(LocationDto dropLocation, Long reservationId);

	Reservations updatePaymentDetails(Long reservationId, String responseTransactionId, String refrenceTransactionId,
			String amount);

}
