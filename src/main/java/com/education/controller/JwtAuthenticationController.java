package com.education.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.education.configuration.JwtTokenUtil;
import com.education.entity.SignEntity;
import com.education.model.JwtRequest;
import com.education.model.Response2;
import com.education.model.ResponseForToken;
import com.education.model.ResponseModel;
import com.education.model.ResponseTokennn;
import com.education.repository.UserRepo;

import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Tag(name = "Login-API")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationController.class);

	private UserDetails userDetails;
	@Autowired
	private UserRepo userRepo;

	
	// for json
	@PostMapping(value = "/authenticatebyjson")
	@Operation(summary="to authenticate by json",description="this api is used to login by email")
	public ResponseEntity<Object> createAuthenticationTokenWithPath(@RequestBody JwtRequest authenticationRequest) throws Exception {
    try 
    {
		log.info("from by json variable login usrname:{} {}", authenticationRequest.getEmail(),
				authenticationRequest.getPassword());
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		Optional<SignEntity> signOptional=userRepo.findByEmail(authenticationRequest.getEmail());
            if(signOptional.isPresent())
            {
            	SignEntity signEntity=signOptional.get();
            	if(token!=null)
            	{
        	 ResponseModel responseModelData=new ResponseModel();
        	 responseModelData.setToken(token);
        	 responseModelData.setFlagOfUser(signEntity.getFlagOfUser());
        	 return ResponseTokennn.generateResponse(responseModelData, HttpStatus.OK, "200");
            	}
            }
            return ResponseForToken.generateResponse("", HttpStatus.INTERNAL_SERVER_ERROR, "500");
         } catch(Exception e)
          {
        	 return Response2.generateResponse("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED, "000");
          }
	}

	@GetMapping(value = "/refreshtoken")
	@Operation(summary="to refresh the token",description="this api is used to refresh the token")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
		if (claims == null) {
			return Response2.generateResponse("Token is already valid ", HttpStatus.UNAUTHORIZED, "000");
		} else {
			Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
			String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
			return ResponseForToken.generateResponse(token, HttpStatus.OK, "200");
		}
	}
	
	private void authenticate(String email, String password) {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("INVALID_CREDENTIALS", e);
		}
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
}

