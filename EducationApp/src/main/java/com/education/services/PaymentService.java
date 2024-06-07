package com.education.services;



import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
//import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

import com.education.entity.PaymentEntity;
import com.education.repository.PaymentRepo;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;


@Service
public class PaymentService {

	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(PaymentService.class);
	
	
	@Autowired
	private PaymentRepo paymentRepo;
//	
//	public String savePayment(String amount, String mobileNumber)
//	{
//	
//		try
//		{
//			int am=Integer.parseInt(amount)*100;
//		RazorpayClient clint = new RazorpayClient("rzp_test_4rMwZyqpMHTZUg", "YC4JaWkDYtWyPRV43NMFgCnm");
//        JSONObject orderRequest = new JSONObject();
//		//orderRequest.put(am,);
//       // orderRequest.put
//        orderRequest.put("amount",am);
//		orderRequest.put("currency","INR");
//		orderRequest.put("receipt","receipt#1");
//		Order order=clint.orders.create(orderRequest);
//		 System.out.println(order);
//		PaymentEntity paymentEntity=new PaymentEntity();
//		//paymentEntity.se
//		paymentEntity.setOrderId(order.get("id").toString());
//		paymentEntity.setStatus(order.get("status").toString());
//		paymentEntity.setMobileNumber(mobileNumber);
//		paymentEntity.setAttempts(order.get("attempts").toString());
//		LocalDateTime localDateTime=LocalDateTime.now();
//		paymentEntity.setCreatedAt(localDateTime);
//		paymentRepo.save(paymentEntity);
//		return order.toString();
//		} catch(Exception e)
//		{
//			log.error("There  is an issue for payment details : {}",e.getMessage());
//		    return "Error";
//			
//		}
//	}
	public String paymentSucess(String paymentId,String orderId)
	{
		try
		{
			
	   PaymentEntity payment=paymentRepo.findByOrderId(orderId);
	   if(payment!=null)
	   {
	    payment.setPaymentId(paymentId);
	    payment.setStatus("success");
	    payment.setAttempts("1");
	    paymentRepo.save(payment);
	   return "Success";
	   }
	   else
	   {
		   log.error("Their is an error for payment id : " +orderId);
		   return "Error";
	   }
		} catch(Exception e)
		{
			log.error("There is an error got payment get failed and so on : {}",e.getMessage());
			return "Error";
		}
	
	
	}
	
	public String verifyPaymentWithMobileNumber(String mobileNumber)
	{
		PaymentEntity pay=paymentRepo.findByMobileNumber(mobileNumber);
		   if("success".equals(pay.getStatus()))
				   {
			        return "Success";
				   }
		   else
		   {
			   return "failure";
		   }
	}
	
	

	public String createOderForPayment(int payRupee, String userCode) {

		try {
			RazorpayClient clint = new RazorpayClient("rzp_test_4rMwZyqpMHTZUg", "YC4JaWkDYtWyPRV43NMFgCnm");
			JSONObject orderRequest = new JSONObject();
			// int rupee = Integer.parseInt(payRupee);
			orderRequest.put("amount", payRupee * 100);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "receipt#1");
			Order order = clint.orders.create(orderRequest);
			if (order == null) {
				log.error("ERROR | payments oder_id not created !");
				throw new IllegalAccessException("Opps! razorpay error");
			}
			log.info("payment Oder_id : " + order.toString());

//			PaymentHandler paymentData = new PaymentHandler();
//			paymentData.setAmount(order.get("amount").toString());
//			paymentData.setOrderId(order.get("id"));
//			paymentData.setStatus(order.get("status"));
//			paymentData.setAttempts(order.get("attempts").toString());
//			paymentData.setCustomerUserCode(userCode);
//			LocalDateTime localDateTime = LocalDateTime.now();
//			paymentData.setCreatedAt(localDateTime);
//
//			PaymentHandler saveData = this.paymentHandlerRepo.save(paymentData);
//
//			if (saveData == null) {
//				log.error("ERROR | payment data not saved");
//				throw new IllegalAccessError("Opps! Database Error! ");
//			}
//			log.info("Massege | payment order_id created and save successfully");
			return order.toString();

		} catch (Exception e) {
			log.error("ERROR | payment data not saved");
			e.printStackTrace();
			throw new DataRetrievalFailureException("Failed to retrieve data"  +e.getMessage());
		}
	}
}


