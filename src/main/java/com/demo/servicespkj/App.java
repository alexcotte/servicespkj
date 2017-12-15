package com.demo.servicespkj;


import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;
import static spark.Spark.path;
import static spark.Spark.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.servicespkj.db.Login;
import com.demo.servicespkj.security.Jwt;
import com.demo.servicespkj.util.Message;
import com.google.gson.Gson;

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
			return Login.doLogin(username, password);
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
