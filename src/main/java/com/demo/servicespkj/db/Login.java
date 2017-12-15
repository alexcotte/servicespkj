package com.demo.servicespkj.db;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.integration.ConnectionProvider;
import org.codejargon.fluentjdbc.api.integration.providers.DataSourceConnectionProvider;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.codejargon.fluentjdbc.api.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.servicespkj.model.User;
import com.demo.servicespkj.security.Jwt;

public class Login {
	
	
	static Logger log = LoggerFactory.getLogger(Login.class);
	
	public static  final String OK    	 = "OK";
	public static  final String BAD   	 = "BAD_CREDENTIALS";
	public static  Query query = null;
	
	static {
		ConnectionProvider cp = new DataSourceConnectionProvider(DbUtil.getInstance().getDataSource());
		query = (new FluentJdbcBuilder().connectionProvider(cp).build()).query();
	}
	
	public static String doLogin(String user, String password) {
		try {
			Optional<User> first = query.select("SELECT * FROM login WHERE mail = ?").params("alexanortega@gmail.com").firstResult(mapper);
			
			
			
			return Jwt.creacte(user);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return BAD;
	}
	
	
	public static Mapper<User> mapper = rs -> {
		return new User(
					rs.getString("mail"),
					rs.getString("menber"),
					rs.getString("name"),
					rs.getString("lastname"),
					rs.getString("password"),
					rs.getString("phone")
				);
	};
	
}
