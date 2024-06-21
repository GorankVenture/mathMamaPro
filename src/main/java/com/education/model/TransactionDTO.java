package com.education.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDTO {

	
	
	private Long id;
	private String transactionId;
	private String email;
	private double price;
	private  Long courseId;
	private String transactionDate;
	private Long globalId;
	private LocalDateTime createdAt;


}
