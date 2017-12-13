package com.demo.servicespkj;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class Jwt {
	
	static Logger log = LoggerFactory.getLogger(Jwt.class);
	
	
	private static String SECRET ="MUY_SECRETO";
	static String creacte(String id, String user) throws UnsupportedEncodingException {
		
		Calendar calendar = Calendar.getInstance();
		Date currentdate = new Date(calendar.getTimeInMillis());
		calendar.add(Calendar.MINUTE, 30);
		Date expiressAt  = new Date(calendar.getTimeInMillis());
		
		return Jwts.builder()
                .setSubject("PLICA")
                .setAudience("PLICA")
                .claim("ROLE","USER")
                .claim("NAME", user)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                .compact();
	}
	
	static boolean verify(String token) {
		try {
			Jws<Claims> a = Jwts.parser().
			setSigningKey(SECRET)
			.requireAudience("PLICA")
			.require("ROLE", "USER")
			.setSigningKey(SECRET.getBytes("UTF-8"))
			.parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
