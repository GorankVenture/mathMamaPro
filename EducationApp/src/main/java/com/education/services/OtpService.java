package com.education.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.OtpEntity;
import com.education.entity.SignEntity;
import com.education.model.EmailDTO;
import com.education.model.VerifyEmailOtpDTO;
import com.education.repository.OtpRepo;
import com.education.repository.UserRepo;
@Service
public class OtpService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OtpService.class);
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpRepo otpRepo;
	public EmailDTO emailByOtp(EmailDTO emailDTO)
	{
		try
		{
		String email=emailDTO.getEmail();
		//String otp=generateRandomOtp();
		
		Optional<SignEntity> userOptional=userRepo.findByEmail(email);
		SignEntity dataUser=userOptional.get();
		if(dataUser!=null)
		{
			
			String otp=generateRandomOtp();
			OtpEntity oEntity=new OtpEntity();
			oEntity.setEmail(dataUser.getEmail());
			oEntity.setOtp(otp);
			LocalDateTime currentDateTime = LocalDateTime.now();
			LocalDateTime otpSendStringFormatted = currentDateTime.plusMinutes(1);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpSendString = otpSendStringFormatted.format(formatter);
			oEntity.setOtpSend(otpSendString);

			LocalDateTime currentdateTime = LocalDateTime.now();
			LocalDateTime otpExpiryStringFormatted = currentdateTime.plusMinutes(1);

			DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String otpExpiryString = otpExpiryStringFormatted.format(formater);
			oEntity.setOtpExpiry(otpExpiryString);
			otpRepo.save(oEntity);
			sendOtpByEmail(dataUser.getEmail(),otp);
			log.info("Email with otp : {}",email);
			emailDTO.setOtp(otp);
	
		}
		    else
		    {
			log.error("Email do not have otp : {}",email);
			return null;
		   }
		
		return  emailDTO;
		} catch(Exception e)
		{
			log.error("There is a error of sign in otp :", e.getMessage());
			return null;
		}
	}
	private void sendOtpByEmail(String email, String otp) {

		String subject = "OTP verification for email";
		String message = "<html><body>" + "<p>Dear User,</p>" + "<p>Your OTP for verification is: <strong>" + otp
				+ "</strong></p>" + "<p>Please use this OTP within 1 minute to complete your verification process.</p>"
				+ "<p>If you didn't request this OTP, please ignore this email.</p>" + "<p>Best regards,</p>"
				+ "<p>Venture Consulting Services</p>" + "</body></html>";

		emailService.sendEmail(subject, message, email);
	}
	
	
	private String generateRandomOtp()
	{
		Random random=new Random();
		int otpValue=1000+random.nextInt(9999);
		return String.valueOf(otpValue);
	}
	public String  verifyOtpByEmail(VerifyEmailOtpDTO  verifyEmailDTO)

	{    
		Optional<OtpEntity> otpOptional=otpRepo.findByEmailAndOtp(verifyEmailDTO.getEmail(),verifyEmailDTO.getOtp());
		if (otpOptional.isPresent())
		{
			return "Success";
		}
		else
		{
			return "Invalid otp";
		}
	}
//			OtpEntity oe=otpOptional.get();
//			if(verifyEmailDTO.getOtp().equals(oe.getOtp()) && oe.getOtpExpiry()!=null)
//			{
//				oe.setOtp(verifyEmailDTO.getOtp());
//				String otpexpiry=oe.getOtpExpiry();
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				LocalDateTime otpExpiry = LocalDateTime.parse(otpexpiry, formatter);
//
//				LocalDateTime currentDateTime = LocalDateTime.now();
//				if (otpExpiry.isAfter(currentDateTime)) {
//
//					System.out.println("otpExpiry get expire after the current time");
//					//return "Success";
//				}
//				else if(otpExpiry.isBefore(currentDateTime))
//				{
//					System.out.println("otpexpiry get expire before the current time");
//					//return "Invalid OTP";
//				}
//				else {
//
//					System.out.println("otpExpiry is not equal to current DateTime");
//
//				}
//				oe.setOtpExpiry(otpexpiry);
//				otpRepo.save(oe);
//				System.out.println("Verify otp" +verifyEmailDTO.getEmail()+","+verifyEmailDTO.getOtp());
//				log.info("Verify otp : " + verifyEmailDTO.getEmail() + ", " + verifyEmailDTO.getOtp());
//				return "Success";
//			}
//			else
//			{
//				return "It is wrong";
//			}
//		}
//		else
//		{
//			return "User is not found";
//		}
//	}catch(Exception e)
//	{
//		log.error("Error in sign in for otp" +e.getMessage()) ;
//		return "Error";
//	}
//	}
}
