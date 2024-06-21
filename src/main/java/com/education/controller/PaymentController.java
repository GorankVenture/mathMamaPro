package com.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.Response2;
import com.education.services.PaymentService;

import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="Payment-API")
public class PaymentController {

	
	@Autowired
    private	PaymentService paymentService;
	@PostMapping(value="/createPayment")
	public ResponseEntity<?> createPayment(@RequestParam ("amount") String amount, @RequestParam ("mobileNumber") String mobileNumber)
	{
//		String data=paymentService.savePayment(amount, mobileNumber);
		try {
			String data = this.paymentService.createOderForPayment(Integer.parseInt(amount), mobileNumber);
			System.out.print(data);
			return Response2.generateResponse(data,HttpStatus.OK,"200");
			
		} catch (Exception e) {
			return Response2.generateResponse("Payment is not created", HttpStatus.BAD_REQUEST, "400");
		}
		
		
		
		
	}
	
	/*public ResponseEntity<?> createPaymentSuccess(@RequestParam ("paymentId") String paymentId,@RequestParam ("orderId") String orderId)
	{
		String p=paymentService.paymentSuccess(paymentId, orderId);
		if("Success".equals(p))
		{
			return Response2.generateResponse("Payment added successfully", HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Payment Failed!", HttpStatus.BAD_REQUEST, "400");
		}
	}*/
	
	@PostMapping(value="/paymentSuccess")
	public ResponseEntity<?> createPaymentSuccess(@RequestParam ("paymentId") String paymentId,@RequestParam("orderId") String orderId)
	{
		String p=paymentService.paymentSucess(paymentId, orderId);
		if("Success".equals(p))
		{
			return Response2.generateResponse("Payment get successful", HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Payment failed!", HttpStatus.BAD_REQUEST, "400");
		}
	}
}
