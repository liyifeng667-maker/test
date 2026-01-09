package com.lanfan.zhgd.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson2.JSONObject;

public class HttpHelper {

	public static JSONObject sendGetRequest(String url,HttpHeaders headers){
        RestTemplate client = new RestTemplate();
        HttpMethod method = HttpMethod.GET;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        ResponseEntity<JSONObject> result = client.exchange(url, method, entity, JSONObject.class);
        if(result.getBody().getString("code").equals("200")){
        	 return  result.getBody();
        }
        else{
        	return new JSONObject();
        }
    }
	
	public static JSONObject sendPostRequest(String url, JSONObject json, HttpHeaders headers){
        RestTemplate client = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
        JSONObject result = client.postForObject(url, entity, JSONObject.class);
        if(result.getString("code").equals("200")){
        	return result;
        }
        else{
        	return new JSONObject();
        }
    }
}