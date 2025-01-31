package com.car.rentalservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.CustomerDto;
import com.car.rentalservice.emums.CustomerStatus;
import com.car.rentalservice.exception.ConflictException;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.mapper.CustomerMapper;
import com.car.rentalservice.model.Customers;
import com.car.rentalservice.model.Status;
import com.car.rentalservice.model.Users;
import com.car.rentalservice.repository.CustomersRepo;
import com.car.rentalservice.repository.StatusRepo;
import com.car.rentalservice.repository.UsersRepo;
import com.car.rentalservice.service.CustomersService;

import jakarta.transaction.Transactional;

@Service
public class CustomersServiceImpl implements CustomersService {
	
	@Autowired private CustomersRepo customerRepo;
	@Autowired private UsersRepo userRepo;
	@Autowired private StatusRepo statusRepo;
	@Autowired private CustomerMapper customerMapper;

	@Override
	@Transactional
	public Customers createCustomer(CustomerDto customer, String username) {
		Users user = userRepo.findById(username)
				.orElseThrow(() -> new NotFoundException("User not Found"));
		if (user.getCustomer() != null )
	        throw new ConflictException("Customer already exists for this user.");
	    else if(user.getOtp() == null || !user.getOtp().equals("1"))
	    	throw new ConflictException("OTP Not verified");
		
		Customers newCustomer = customerMapper.mappToNewCoustomer(customer);
		Status status = statusRepo.findById(CustomerStatus.Active.getCode())
				.orElseThrow(() -> new NotFoundException("Customer status not found"));
		newCustomer.setStatus(status);
		Customers savedCustomer = customerRepo.save(newCustomer);
		
		user.setCustomer(savedCustomer);
		userRepo.save(user);
		
		return savedCustomer;
	}

	@Override
	public Customers updateCustomer(CustomerDto customer, Long customerId) {
		Customers updateCustomer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));
		return customerRepo.save(customerMapper.mappToUpdateCoustomer(updateCustomer,customer));
	}

	@Override
	public void deleteCustomer(Long customerId) {
		customerRepo.findById(customerId)
			.orElseThrow(() -> new NotFoundException("Customer not found."));
		if(userRepo.updatecustomerIdToNull(customerId) == null)
			customerRepo.deleteById(customerId);
	}

	@Override
	public Customers findCustomer(Long customerId) {
		return customerRepo.findById(customerId)
		.orElseThrow(() -> new NotFoundException("Customer not found."));
	}
	
	
	
}
