package com.demo.servicespkj.util;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonTransform implements ResponseTransformer{

	
	private Gson gson = new Gson();
	
	public String render(Object obj) {
        return gson.toJson(obj);
    }

}
