package com.car.rentalservice.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.AuthUserDto;
import com.car.rentalservice.repository.UsersRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired private UsersRepo userRepo;
	
	@Override
	public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		 AuthUserDto user = userRepo.findByUsername(username)
			        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		
		return new CustomUserDetail(user);
	}

}
