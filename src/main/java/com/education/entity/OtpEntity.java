package com.education.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;





@Getter
@Setter
@Entity
public class OtpEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	private String otp;
	private String otpSend;
	private String otpExpiry;
	
	
}
