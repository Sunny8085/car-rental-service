package com.car.rentalservice.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.car.rentalservice.dto.AuthUserDto;

import lombok.Data;

@Data
public class CustomUserDetail implements UserDetails{

	private static final long serialVersionUID = 1L;
	private AuthUserDto user;
	
	public CustomUserDetail(AuthUserDto user) {
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Roles role=user.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRoleId()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	@Override
	public boolean isEnabled() {
		if (user.getOtp().equals("1")) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

}
