package com.education.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Long courseId;
	private String transactionDate;
	private LocalDateTime createdAt;
	private String transactionCode;

}
