package com.demo.servicespkj.security;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class Jwt {
	
	static Logger log = LoggerFactory.getLogger(Jwt.class);
	
	
	private static String SECRET ="MUY_SECRETO";
	public  static String OK ="OK";

	public static String creacte(String user) throws UnsupportedEncodingException {
		
		Calendar calendar = Calendar.getInstance();
		Date currentdate = new Date(calendar.getTimeInMillis());
		calendar.add(Calendar.MINUTE, 60);
		Date expiressAt  = new Date(calendar.getTimeInMillis());
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("alg", SignatureAlgorithm.HS512.getJcaName());
		header.put("typ", "JWT");
		
		return Jwts.builder()
				.setHeader(header)
                .setSubject(user)
                .setAudience("IPSFA")
                .claim("name","Julanito de Tal")
                .claim("rol", user) //--> as json maybe
				.setIssuedAt(currentdate)
				.setExpiration(expiressAt)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes("UTF-8"))
                .compact();
	}
	
	public static String verify(String token) {
		try {
			token = token.replace("Bearer ", "");
			//Claims claims = 
					Jwts.parser().
			setSigningKey(SECRET)
			.requireAudience("PLICA")
			.require("ROLE", "USER")
			.setSigningKey(SECRET.getBytes("UTF-8"))
			.parseClaimsJws(token).getBody();
			return OK;
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
}
