package com.education.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseWithList {
	public  ResponseEntity<Object> generateResponse(String message, HttpStatus status, String customcode,List<?> list) {
        Map<String, Object> map = new  HashMap<>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("customcode", customcode);
            map.put("result",list);
            HttpHeaders headers2 = new HttpHeaders();
        	headers2.add("Content-Type","application/json");
        	ResponseEntity<Object> resp=  new ResponseEntity<Object>(map, headers2, status.value());
           return resp;
        }

	public ResponseEntity<?> generateResponse(String message, HttpStatus badRequest, String customcode, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}