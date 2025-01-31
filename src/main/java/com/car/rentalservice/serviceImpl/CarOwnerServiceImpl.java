package com.car.rentalservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.CarOwnerDto;
import com.car.rentalservice.emums.CarOwnerStatus;
import com.car.rentalservice.exception.ConflictException;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.mapper.CarOwnerMapper;
import com.car.rentalservice.model.CarOwner;
import com.car.rentalservice.model.Status;
import com.car.rentalservice.model.Users;
import com.car.rentalservice.repository.CarOwnerRepo;
import com.car.rentalservice.repository.StatusRepo;
import com.car.rentalservice.repository.UsersRepo;
import com.car.rentalservice.service.CarOwnerService;

import jakarta.transaction.Transactional;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
	
	@Autowired private CarOwnerRepo carOwnerRepo;
	@Autowired private CarOwnerMapper carOwnerMapper;
	@Autowired private UsersRepo userRepo;
	@Autowired private StatusRepo statusRepo;
	
	@Override
	@Transactional
	public CarOwner createOwner(CarOwnerDto owner, String username) {
		Users user = userRepo.findById(username)
				.orElseThrow(() -> new NotFoundException("User not Found"));
		if (user.getCarOwner() != null )
	        throw new ConflictException("CarOwner already exists for this user.");
	    else if(user.getOtp() == null ||!user.getOtp().equals("1"))
	    	throw new ConflictException("OTP Not verified");
		
		CarOwner carOwner = carOwnerMapper.mappToNewCarOwner(owner);
		Status status = statusRepo.findById(CarOwnerStatus.Active.getCode())
				.orElseThrow(() -> new NotFoundException("CarOwner status not found"));
		carOwner.setStatus(status);
		CarOwner savedCarOwner = carOwnerRepo.save(carOwner);
		
		user.setCarOwner(savedCarOwner);
		userRepo.save(user);
		
		return savedCarOwner;
	}

	@Override
	public CarOwner updateOwner(CarOwnerDto owner, Long carOwnerId) {
		CarOwner updateCustomer = carOwnerRepo.findById(carOwnerId)
				.orElseThrow(() -> new NotFoundException("CarOwner not found"));
		return carOwnerRepo.save(carOwnerMapper.mappToUpdateCarOwner(updateCustomer,owner));
	}

	@Override
	public void deleteOwner(Long customerId) {
		carOwnerRepo.findById(customerId)
		.orElseThrow(() -> new NotFoundException("CarOwner not found."));
		if(userRepo.updateCarOwnerIdToNull(customerId) == null)
			carOwnerRepo.deleteById(customerId);
	}

	@Override
	public CarOwner findOwner(Long customerId) {
		return carOwnerRepo.findById(customerId)
				.orElseThrow(() -> new NotFoundException("CarOwner not found."));
	}

}
