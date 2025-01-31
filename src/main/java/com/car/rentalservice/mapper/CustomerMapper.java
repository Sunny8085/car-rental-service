package com.car.rentalservice.mapper;

import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.CustomerDto;
import com.car.rentalservice.model.Customers;

@Component
public class CustomerMapper {

	public Customers mappToNewCoustomer(CustomerDto customer) {
		Customers newCustomer = new Customers();
		newCustomer.setCustomerName(customer.getName());
		newCustomer.setCustomerEmail(customer.getEmail());
		newCustomer.setCustomerPhone(customer.getPhone());
		newCustomer.setCustomerAddress(customer.getAddress());
		newCustomer.setDriverLicense(customer.getDriverLicenseNumber());
		newCustomer.setDateOfBirth(customer.getDateOfBirth());
		newCustomer.setDistrict(customer.getDistrict());
		newCustomer.setCountry(customer.getCountry());
		newCustomer.setState(customer.getState());
		newCustomer.setPincode(customer.getPincode());
		return newCustomer;
	}
	
	public Customers mappToUpdateCoustomer(Customers updateCustomer,CustomerDto customer) {
		updateCustomer.setCustomerName(customer.getName());
		updateCustomer.setCustomerEmail(customer.getEmail());
		updateCustomer.setCustomerPhone(customer.getPhone());
		updateCustomer.setCustomerAddress(customer.getAddress());
		updateCustomer.setDateOfBirth(customer.getDateOfBirth());
		updateCustomer.setDistrict(customer.getDistrict());
		updateCustomer.setCountry(customer.getCountry());
		updateCustomer.setState(customer.getState());
		updateCustomer.setPincode(customer.getPincode());
		return updateCustomer;
	}
	
}
