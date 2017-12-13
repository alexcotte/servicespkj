package com.demo.servicespkj;


import static spark.Spark.*;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	Logger log = LoggerFactory.getLogger(App.class);

    	path("/api",() ->{
    		before("/*",(q,a)-> {
				String token    = q.headers("Authorization");
				String verified = Jwt.verify(token);
				if( verified != Jwt.OK){
					log.info(token);
					a.type("application/json");
					halt(403, new Gson().toJson(new Message(403,verified)));
				}
			});
    		path("plica",()->{

			});
		});

    	post("/login",(rq,rp)->{
			String username = rq.queryParams("username");
			String password = rq.queryParams("password");
			log.info("Login --> User name="+username+ " password: *************");
			if(username.equals("username") && password.equals("password")){
				log.info("Token");
				return Jwt.creacte(username);
			}
			return "error";
		});


    	
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
