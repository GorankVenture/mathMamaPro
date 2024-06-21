package com.education.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.Transaction;
import com.education.exception.TransactionNotFoundException;
import com.education.model.TransactionDTO;
import com.education.repository.CourseRepo;
import com.education.repository.TransactionRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TransactionService {

	
	@Autowired
	private TransactionRepo transactionRepo;
	
	
	@Autowired
	private CourseRepo courseRepo;
	
	public String saveTransaction(TransactionDTO transactionDTO)
	{
		try
		{
			Transaction transaction=new Transaction();
			transaction.setEmail(transactionDTO.getEmail());
			transaction.setPrice(transactionDTO.getPrice());
			transaction.setCourseId(transactionDTO.getCourseId());
			transaction.setTransactionId(transactionDTO.getTransactionId());
			transaction.setGlobalId(transactionDTO.getGlobalId());
			transaction.setTransactionDate(transactionDTO.getTransactionDate());
			transaction.setPaid(false);
			transactionRepo.save(transaction);
			return "Success";
			
		}  catch(Exception e)
		{
			log.error("There is an error for  saving the details of transaction : {}",e.getMessage());
			return "Error";
		}
		
	}
	
	public List<Transaction> getTransactionByEmail(String email)
	{
		List<Transaction>  transactionList=transactionRepo.findAllByEmail(email);
		return transactionList;
	}

	public Transaction getGlobalIdAndEmail(Long globalId,String email)
	{
		    Transaction tr =transactionRepo.findByGlobalIdAndEmail(globalId,email);
		   if(tr==null)
		   {
			   throw new TransactionNotFoundException("Transaction with globalId " +globalId+   "email" +email +"not found");
		   }
		    tr.setPaid(true);
		    return this.transactionRepo.save(tr);
		   
		  
	}
	public Transaction getCourseIdAndEmail(Long courseId,String email)
	{
		Transaction transactionData=transactionRepo.findByCourseIdAndEmail(courseId,email);
		return transactionData;
	}
	
	
}
