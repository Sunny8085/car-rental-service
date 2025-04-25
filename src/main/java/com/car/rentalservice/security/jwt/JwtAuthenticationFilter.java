package com.car.rentalservice.security.jwt;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LogManager.getLogger(OncePerRequestFilter.class);
	@Autowired	private JwtUtil jwtHelper;
	@Autowired	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader = request.getHeader("Authorization");
		logger.info("Header : {}",requestHeader);
		String username = null;
		String token = null;
		
		if(requestHeader != null && requestHeader.startsWith("Bearer ")) {
			token = requestHeader.substring(7);
			try {
				username = this.jwtHelper.getUsernameFromToken(token);
			}catch(IllegalArgumentException e) {
				logger.info("Illegal Argument while fetching the username !!");
				e.printStackTrace();
			}catch(ExpiredJwtException e) {
				logger.info("Given jwt token is expired !!");
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
				logger.info("Invalid Header Value !!");
		}
			
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
			if(validateToken) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				logger.info("Vlidation fails !!");
			}
		}else {
			System.out.println("Token is not validated.......");
		}
		filterChain.doFilter(request, response);
	}
	
}
