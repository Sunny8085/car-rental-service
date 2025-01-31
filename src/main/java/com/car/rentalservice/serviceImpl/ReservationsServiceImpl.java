package com.car.rentalservice.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.EmailDetails;
import com.car.rentalservice.dto.LocationDto;
import com.car.rentalservice.emums.CustomerStatus;
import com.car.rentalservice.emums.LocationType;
import com.car.rentalservice.emums.ReservationStatusCode;
import com.car.rentalservice.emums.VehicleStatusCode;
import com.car.rentalservice.exception.ConflictException;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.model.Customers;
import com.car.rentalservice.model.Location;
import com.car.rentalservice.model.Rentals;
import com.car.rentalservice.model.Reservations;
import com.car.rentalservice.model.ReservationsStatus;
import com.car.rentalservice.model.Vehicles;
import com.car.rentalservice.repository.CustomersRepo;
import com.car.rentalservice.repository.LocationRepo;
import com.car.rentalservice.repository.RentalsRepo;
import com.car.rentalservice.repository.ReservationStatusRepo;
import com.car.rentalservice.repository.ReservationsRepo;
import com.car.rentalservice.repository.VehiclesRepo;
import com.car.rentalservice.service.ReservationsService;
import com.car.rentalservice.util.EmailService;
import com.car.rentalservice.util.OTPGenerator;

import jakarta.transaction.Transactional;

@Service
public class ReservationsServiceImpl implements ReservationsService{
	
	@Autowired private ReservationsRepo reservationsRepo;
	@Autowired private CustomersRepo customerRepo;
	@Autowired private VehiclesRepo vehiclesRepo;
	@Autowired private ReservationStatusRepo reservationStatusRepo;
	@Autowired private EmailService emailService;
	@Autowired private LocationRepo locationRepo;
	@Autowired private RentalsRepo rentalRepo;

	@Override
	public Reservations getReservaitonById(Long reservationId) {
		return reservationsRepo.findById(reservationId).orElse(null);
	}

	@Override
	public Reservations addReservation(Long vehicleId, Long customerId) {
		Customers customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));
		Vehicles vehicles = vehiclesRepo.findById(vehicleId)
				.orElseThrow(() -> new NotFoundException("Vehicle not found"));
		ReservationsStatus reservationStatus = reservationStatusRepo.findById(Long.valueOf(ReservationStatusCode.PENDING.getStatus_code()))
				.orElseThrow(() -> new NotFoundException("Reservation Status not found"));
		
		if(vehicles.getVehicleStatus().getVehicleStatusCode().equals(VehicleStatusCode.AVAILABLE.getCode()) 
				&& customer.getStatus().getStatus_code().equals(CustomerStatus.Active.getCode())) {
			
			String otp = OTPGenerator.generateOTP();
			sendEmail(otp,customer.getCustomerEmail());
			
			Reservations newReservation = new Reservations();
			newReservation.setCustomer(customer);
			newReservation.setVehicle(vehicles);
			newReservation.setReservationsStatus(reservationStatus);
			newReservation.setReservationsOTP(otp);
			newReservation.setReservationsOTPTime(LocalDateTime.now().plusMinutes(10));
			return reservationsRepo.save(newReservation);
			
		}else {
			if (!vehicles.getVehicleStatus().getVehicleStatusCode().equals(VehicleStatusCode.AVAILABLE.getCode()))
		        throw new ConflictException("Customer with ID " + customerId + " is not available.");
		    if (!customer.getStatus().getStatus_code().equals(CustomerStatus.Active.getCode()))
		        throw new ConflictException("Vehicle with ID " + vehicleId + " is not active.");
		}
		return new Reservations();
	}

	@Override
	public Boolean verfiyOTP(Long reservationId, String otp) {
		Reservations reservation = reservationsRepo.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("Vehicle Reservation not found.")); 
		if(reservation.getReservationsOTPTime().isAfter(LocalDateTime.now()) && reservation.getReservationsOTP().equals(otp))
			return true;
		else
			return false;
	}

	@Override
	public String sendOTP(Long reservationId) {
		Reservations reservation = reservationsRepo.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("Reservation not found."));
		String otp = OTPGenerator.generateOTP();
		sendEmail(otp,reservation.getCustomer().getCustomerEmail());
		return "OTP send successfully";
	}
	
	@Override
	@Transactional
	public Reservations addPickupLocation(LocationDto pickupLocation, Long reservationId) {
		Reservations reservation = reservationsRepo.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("Reservation not found."));
		ReservationsStatus status = reservationStatusRepo.findById(Long.valueOf(ReservationStatusCode.CONFIRMED.getStatus_code()))
				.orElseThrow(() -> new NotFoundException("Status code not found."));
		
		if(reservation.getReservationsStatus().getReservationsStatusCode() != Long.valueOf(ReservationStatusCode.PENDING.getStatus_code()))
			throw new ConflictException("Reservation not done yet.");
		else if(reservation.getReservationsStatus().getReservationsStatusCode() == Long.valueOf(ReservationStatusCode.COMPLETED.getStatus_code()))
			throw new ConflictException("Reservation completed.");
		
		Location newLocation = new Location();
		newLocation.setLocationName(pickupLocation.getLocationName());
		newLocation.setAddress(pickupLocation.getLocationName());
		newLocation.setCity(pickupLocation.getCity());
		newLocation.setState(pickupLocation.getState());
		newLocation.setPincode(pickupLocation.getPincode());
		newLocation.setLocationType(LocationType.PICKUP.toString());
		Location location =  locationRepo.save(newLocation);
		
		reservation.setReservationsStatus(status);
		reservation.setPickupDate(LocalDateTime.now());
		reservation.setPickupLocation(location);
		Reservations newReservation = reservationsRepo.save(reservation);
		
		Rentals rental = new Rentals();
		rental.setReservaton(reservation);
		rental.setVehicles(reservation.getVehicle());
		rentalRepo.save(rental);
		
		return newReservation;
	}
	
	@Override
	@Transactional
	public Reservations addDropoffLocation(LocationDto dropLocation, Long reservationId) {
		Reservations reservation = reservationsRepo.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("Reservation not found."));
		ReservationsStatus status = reservationStatusRepo.findById(Long.valueOf(ReservationStatusCode.AWAITING_PAYMENT.getStatus_code()))
				.orElseThrow(() -> new NotFoundException("Status code not found."));
		Rentals rental = rentalRepo.findByReservatonReservationId(reservation.getReservationId());
		if(rental == null)
			throw new NotFoundException("Rental not found.");
			
		if(reservation.getReservationsStatus().getReservationsStatusCode() == Long.valueOf(ReservationStatusCode.COMPLETED.getStatus_code()))
			throw new ConflictException("Reservation completed.");
		
		Location newLocation = new Location();
		newLocation.setLocationName(dropLocation.getLocationName());
		newLocation.setAddress(dropLocation.getLocationName());
		newLocation.setCity(dropLocation.getCity());
		newLocation.setState(dropLocation.getState());
		newLocation.setPincode(dropLocation.getPincode());
		newLocation.setLocationType(LocationType.DROP_OFF.toString());
		Location location =  locationRepo.save(newLocation);
		
		rental.setReservaton(reservation);
		rental.setVehicles(reservation.getVehicle());
		rental.setReturnDate(LocalDateTime.now());
		rental.setTotalAmount(dropLocation.getAmount());
		rentalRepo.save(rental);
		
		reservation.setAmount(dropLocation.getAmount());
		reservation.setReservationsStatus(status);
		reservation.setPickupDate(LocalDateTime.now());
		reservation.setDropoffLocation(location);
		return reservationsRepo.save(reservation);
	}
	
	@Override
	public Reservations updatePaymentDetails(Long reservationId, String responseTransactionId,
			String refrenceTransactionId, String amount) {
		Reservations reservation = reservationsRepo.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("Reservation not found."));
		ReservationsStatus status = reservationStatusRepo.findById(Long.valueOf(ReservationStatusCode.COMPLETED.getStatus_code()))
				.orElseThrow(() -> new NotFoundException("Status code not found."));
		
		if(responseTransactionId != null && amount != null && reservation.getPaymentStatus() == null && amount.equals(reservation.getAmount())) {
			reservation.setReservationsStatus(status);
			reservation.setRefrenceTransactionId(refrenceTransactionId);
			reservation.setResponseTransactionId(responseTransactionId);
			reservation.setPaymentStatus("21");
			reservation.setAmount(amount);
			return reservationsRepo.save(reservation);
		}else
			return new Reservations();
	}
	
	private void sendEmail(String otp,String email) {
		String body = "Dear email, \n\n"+"We received a request verify car rental reservation."
				+ "Please use the following One-Time Password (OTP) to proceed car reservation: \n\n **OTP:**" +otp+ "\n\n"
				+ "This OTP is valid for the next 10 minutes. Please do not share this OTP with anyone. "
				+ "If you did not request for car reservation, please ignore this email or contact our support team immediately.\n\n"
				+ "Best regards,\n Car Rental Service Support Team";
		EmailDetails detail = new EmailDetails();
		detail.setRecipient(email);
		detail.setMsgBody(body);
		detail.setSubject("Car Rental Service OTP");
		emailService.sendSimpleMail(detail);
	}

}









