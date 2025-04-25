package com.car.rentalservice.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private String SECRET_KEY = "yourBase64EncodedPrivateKeysasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd";
	private static final long JWT_TOKEN_VALIDITY = 150 * 60 * 60; 

	//fetch token's username
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	 
	//fetch token's expiration
	public Date getExpirationDateFromToken(String token) {
		Date d=getClaimFromToken(token,Claims::getExpiration);
		return d;
	}
	
	//validation
	public <T>T getClaimFromToken(String token,Function<Claims, T> claimsResolver){
		final Claims claims= getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY).build()
				.parseClaimsJws(token).getBody();
	}
		 
	 private Boolean isTokenExpired(String token) {
		 final Date expiration = getExpirationDateFromToken(token);
		 Date curr = new Date();
		 Boolean expi=expiration.before(curr);
		 return expi;
	 }
	
	 
	 public Boolean validateToken(String token,UserDetails userDetails) {
		 final String username = getUsernameFromToken(token);
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	 } 
		 
	//token generation
	 public String generateToken(UserDetails userDetails) {
		 Map<String,Object> claims=new HashMap<String, Object>();
		 List<String> roles = userDetails.getAuthorities()
			        .stream()
			        .map(GrantedAuthority::getAuthority)
			        .collect(Collectors.toList());
		 claims.put("roles", roles);
		 return doGenerateToken(claims, userDetails.getUsername());
	 }
	
	public String doGenerateToken(Map<String,Object> claims,String user) {
		 return Jwts.builder()
				 .setClaims(claims)
				 .setSubject(user)
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
				 .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
				 .compact();
	 }
	 //end of Token genetation
	
}
