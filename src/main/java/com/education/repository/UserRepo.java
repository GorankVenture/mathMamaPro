package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.SignEntity;

public interface  UserRepo extends JpaRepository<SignEntity,Long> 

{
	
	  Optional<SignEntity> findByMobileNumber(String mobileNumber);
     Optional<SignEntity> findByEmail(String email);
     //Optional<SignEntity> findByEmailAndOtp(String email, String otp);
}
