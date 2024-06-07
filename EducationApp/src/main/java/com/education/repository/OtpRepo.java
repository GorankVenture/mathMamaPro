package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.OtpEntity;

public interface OtpRepo extends JpaRepository<OtpEntity,Long>
{
  Optional<OtpEntity> findByEmailAndOtp(String email,String otp);
}
