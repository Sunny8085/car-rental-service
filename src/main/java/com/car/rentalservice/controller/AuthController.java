package com.car.rentalservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rentalservice.dto.AuthUserDto;
import com.car.rentalservice.security.CustomUserDetail;
import com.car.rentalservice.security.UserDetailsServiceImpl;
import com.car.rentalservice.security.jwt.JwtAuthResponse;
import com.car.rentalservice.security.jwt.JwtUtil;

@RestController
public class AuthController {
	
	@Autowired private UserDetailsServiceImpl userDetailsService;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private JwtUtil jwtTokenHelper;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam String username,@RequestParam String password ) throws Exception{
		this.authenticate(username, password);
		final CustomUserDetail userDetails = userDetailsService.loadUserByUsername(username);
		final String token = jwtTokenHelper.generateToken(userDetails);
		
		Map<String, Object> mp = new HashMap<>();
		AuthUserDto user = userDetails.getUser();
		mp.put("role", user.getRoleName());
		
		return ResponseEntity.ok(new JwtAuthResponse(token,mp));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(upat);	
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	
}
