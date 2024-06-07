package com.education.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.Transaction;
import com.education.model.TransactionDTO;
import com.education.repository.TransactionRepo;


@Service
public class TransactionService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TransactionService.class);
	@Autowired
	private TransactionRepo transactionRepo;
	
	public String saveTransaction(TransactionDTO transactionDTO)
	{
		try
		{
			Transaction transaction=new Transaction();
			transaction.setEmail(transactionDTO.getEmail());
			transaction.setPrice(transactionDTO.getPrice());
			transaction.setCourseId(transactionDTO.getCourseId());
			transaction.setTransactionId(transactionDTO.getTransactionId());
			transaction.setApproved(false);
			transaction.setTransactionCode(transactionDTO.getTransactionCode());
			transactionRepo.save(transaction);
			return "Success";
			//transaction.setCurrentDateTime(null);
		}  catch(Exception e)
		{
			log.error("There is an error for  saving the details of transaction : {}",e.getMessage());
			return "Error";
		}
		
	}
	
	
	public  boolean approveTransaction(String transactionId)
	{
		Transaction transactionData=transactionRepo.findByTransactionId(transactionId);
		//Transaction transactionData=transactionOptional.get();
		if(transactionData!=null)
		{
			transactionData.setApproved(true);
			transactionRepo.save(transactionData);
			return true;
		}
		return false;
		
	}
	public boolean checkTransactionApproved(String transactionId)
	{
		Transaction transactionResponse=transactionRepo.findByTransactionId(transactionId);
		return transactionResponse!=null && transactionResponse.isApproved();
	}
}
