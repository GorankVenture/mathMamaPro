package com.education.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.education.entity.SignEntity;
import com.education.model.RegistrationModel;
import com.education.repository.UserRepo;
@Service
public class RegistrationService {

	
	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationService.class);
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	ModelMapper mapper;
	
	public String registerUser(RegistrationModel reg)
	{
		try
		{
		SignEntity usEntity=mapper.map(reg,SignEntity.class);
		usEntity.setName(reg.getName());
		usEntity.setEmail(reg.getEmail());
		usEntity.setPassword(bcryptEncoder.encode(reg.getPassword()));
		userRepo.save(usEntity);
		return "Success";
		} catch(Exception e)
		{
			e.printStackTrace();
			log.error("There is an error in registration : {}",e.getMessage());
			return "Error";
		}
		
	}
	public String findByMobile(RegistrationModel reg)
	{
		try
		{
			Optional<SignEntity> mobileCheck=userRepo.findByMobileNumber(reg.getMobileNumber());
		   if(mobileCheck.isPresent())
		   {
			   return "A";
		   }
		   else
		   {
			   return "NA";
		   }
		} catch(Exception e)
		{
			e.printStackTrace();
			log.error("There is an exception registering the user in mobile number: {}",e.getMessage());
			return "Error";
		}
	}
}
