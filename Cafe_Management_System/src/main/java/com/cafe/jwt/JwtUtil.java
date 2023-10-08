package com.cafe.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanRegistration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cafe.pojo.User;
import com.google.common.base.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	// create secret key

	private String secret = "mani@1234";

	public String extractUsername(String token) {
		return extractClamis(token, Claims::getSubject);
	}

	// Expiration token...
	public Date extractExpiration(String token) {
		return extractClamis(token, Claims::getExpiration);
	}

	//

	public <T> T extractClamis(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);

	}

	Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username, String role) {
		Map<String, Object> claims = new HashMap<>();

		claims.put("role", role);
		return createToken(claims, username);

	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))// 10hr
				.signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

}
