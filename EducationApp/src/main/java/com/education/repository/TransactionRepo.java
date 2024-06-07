package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.education.entity.Transaction;

public interface TransactionRepo extends JpaRepository <Transaction,Long> 
{

	//@Query("SELECT  t FROM Transaction t WHERE t.transactionId=  :transactionId")
	Transaction findByTransactionId(String transactionId);
	//Transaction findByTransactionCode(String transactionCode);
}
