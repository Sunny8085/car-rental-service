package com.car.rentalservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestApiResponse {
	
	private boolean success;
	
	private Object data;
	
}
