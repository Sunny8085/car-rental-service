package com.car.rentalservice.security.jwt;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse  {
	
	private String token;
	
	private Map<String, Object> data;


}