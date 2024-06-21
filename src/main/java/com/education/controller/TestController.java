package com.education.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "TESTING-API")
public class TestController {

	
	
	
	@GetMapping("/status")
	@Operation(summary="to test api",description="this api is used to test api")
	public ResponseEntity<Object> test1() {
		return new ResponseEntity<>("hi everyone", HttpStatus.OK);
	}
}
