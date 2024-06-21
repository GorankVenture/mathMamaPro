package com.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.Transaction;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.model.TransactionDTO;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="Transaction-API")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping(value="/createTransaction")
	@Operation(summary="to  create the transaction", description="this api is used to create the transaction details and so on")
	public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transactionDTO)
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
	@GetMapping(value="/getAllPaymentsLeft")
	public ResponseEntity<Object> getAllPayments(@RequestParam ("email") String email)
	{
		List<Transaction>  responseData=transactionService.getTransactionByEmail(email);
		return new ResponseWithList().generateResponse("Transaction is unpaid",HttpStatus.OK,"200",responseData);
		
	}
	
	@PostMapping(value="/updateGlobalIdAndEmail")
	public ResponseEntity<Object>updateGlobalIdAndEmail(@RequestParam ("globalId") Long globalId, @RequestParam ("email") String email)
	{
		Transaction transaction=transactionService.getGlobalIdAndEmail(globalId, email);
		if(transaction!=null)
		   {
		     return Response2.generateResponse("Transaction get approved", HttpStatus.OK,"200");
		    }
		  else
		   {
		      return Response2.generateResponse("transaction is not approved", HttpStatus.BAD_REQUEST, "400");
		   }
	}
}
