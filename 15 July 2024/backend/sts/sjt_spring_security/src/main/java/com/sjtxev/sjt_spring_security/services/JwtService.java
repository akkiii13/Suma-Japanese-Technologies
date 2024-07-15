package com.sjtxev.sjt_spring_security.services;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.builder().setSigningKey(getSignInKey())
				.build().parseClimsJwt(token).getBody();
	}
	
}
