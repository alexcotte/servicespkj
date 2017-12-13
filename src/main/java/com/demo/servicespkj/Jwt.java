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
	public  static String OK ="OK";

	static String creacte(String user) throws UnsupportedEncodingException {
		
		Calendar calendar = Calendar.getInstance();
		Date currentdate = new Date(calendar.getTimeInMillis());
		calendar.add(Calendar.MINUTE, 30);
		Date expiressAt  = new Date(calendar.getTimeInMillis());
		
		return Jwts.builder()
                .setSubject("PLICA")
                .setAudience("PLICA")
                .claim("ROLE","USER")
                .claim("NAME", user)
				.setIssuedAt(currentdate)
				.setExpiration(expiressAt)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                .compact();
	}
	
	static String verify(String token) {
		try {
			Jws<Claims> a = Jwts.parser().
			setSigningKey(SECRET)
			.requireAudience("PLICA")
			.require("ROLE", "USER")
					.requireExpiration(new Date())
			.setSigningKey(SECRET.getBytes("UTF-8"))
			.parseClaimsJws(token);
			return OK;
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
}
