package com.demo.servicespkj;


import static spark.Spark.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Logger log = LoggerFactory.getLogger(App.class);
    	
    	post("/login", "application/json", (request, response) -> {
    		String token = Jwt.creacte("10", "usuario");
    		log.info(""+Jwt.verify(token));
    		
    	    return new Message(200,":)");
    	}, new JsonTransform());

    	post("/logout", "application/json", (request, response) -> {
    	    return new Message(200,":)");
    	}, new JsonTransform());

    	
    	notFound((req, res) -> {
    	    res.type("application/json");
    	    res.status(404);
    	    return "{\"code\":\"404\",\"message\":\"Not Found\"}";
    	});
    	
    	internalServerError((req, res) -> {
    	    res.type("application/json");
    	    res.status(500);
    	    return "{\"code\":\"500\",\"message\":\"Internal Error\"}";
    	});
    }
}
