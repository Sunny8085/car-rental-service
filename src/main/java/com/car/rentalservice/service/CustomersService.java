package com.car.rentalservice.service;

import com.car.rentalservice.dto.CustomerDto;
import com.car.rentalservice.model.Customers;

public interface CustomersService {

	Customers createCustomer(CustomerDto customer, String username);

	Customers updateCustomer(CustomerDto customer, Long customerId);

	void deleteCustomer(Long customerId);

	Customers findCustomer(Long customerId);

}
