package com.car.rentalservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.car.rentalservice.emums.Role;
import com.car.rentalservice.security.jwt.JwtAuthentcationEntryPoint;
import com.car.rentalservice.security.jwt.JwtAuthenticationFilter;

@Configuration
@Profile("doc")
public class SecurityConfigurationDoc {

	@Autowired private JwtAuthenticationFilter filter;
	@Autowired private JwtAuthentcationEntryPoint point;
	@Autowired private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.authorizeHttpRequests(auth -> auth
//					.requestMatchers("/api/v1/carowner/**").hasRole("USER")
					.requestMatchers("/api/v1/carowner/**").hasAuthority(Role.CAROWNER.getCode())
					.requestMatchers("/api/v1/customers/**").hasAuthority(Role.USER.getCode())
					.requestMatchers("/api/v1/employees/**").hasAuthority(Role.EMPLOYEE.getCode())
					.requestMatchers("/api/v1/maintenance/**").hasAuthority(Role.CAROWNER.getCode())
					.requestMatchers("/api/v1/reservations/**").hasAuthority(Role.USER.getCode())
					.requestMatchers("/api/v1/users/**").hasAuthority(Role.USER.getCode())
					.requestMatchers("/authenticate").permitAll()
					.requestMatchers("/error").permitAll()
					.requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-resources/**","/webjars/**").permitAll()
					.anyRequest().authenticated())
			.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider doDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider  = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
