package com.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.Response2;
import com.education.model.ResponseWithObject;
import com.education.model.TransactionDTO;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("/auth")
@Tag(name="Transaction-API")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping(value="/createTransaction")
	@Operation(summary="to  create the transaction", description="this api is used to create the transaction details and so on")
	public ResponseEntity<?>  createTransaction(@RequestBody TransactionDTO transactionDTO)
	{
		String response=transactionService.saveTransaction(transactionDTO);
		if("Success".equals(response))
		{
			return new ResponseWithObject().generateResponse("Transaction details are saved",HttpStatus.OK,"200",transactionDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Transaction details are not saved",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	@PostMapping(value="/approveTransactionProcess")
	@Operation(summary="to approve the transaction process",description="this api is used to approve the transaction process")
	public  ResponseEntity<?> approveTransactionProcess(@RequestParam ("transactionId") String transactionId)
	{
		boolean approved=transactionService.approveTransaction(transactionId);
		  if (approved)
		  {
			return Response2.generateResponse("Transaction approved successfully", HttpStatus.OK, "200"); 
		  }
		else
		{
			return Response2.generateResponse("Transaction failed approved", HttpStatus.BAD_REQUEST,"400");
			
		}
	}
}
