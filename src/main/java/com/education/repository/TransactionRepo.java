package com.education.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.education.entity.Transaction;


public interface TransactionRepo extends JpaRepository <Transaction,Long> 
{

	Transaction findByGlobalIdAndEmail(Long globalId,String email);
 	Transaction findByCourseIdAndEmail(Long courseId,String email);
	Transaction findByEmail(String email);
	List<Transaction> findAllByEmail(String email);
}

