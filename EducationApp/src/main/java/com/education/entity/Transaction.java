package com.education.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="transaction_details")
@Getter
@Setter
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(unique=true)
	//@Column(unique=true)
	private String transactionId;
	@Column(name="email")
	private String email;
	@Column(name="price")
	private double price;
	@Column(name="course_id")
	private Long courseId;
	@Column(name="transaction_date")
	private String transactionDate;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@Column(name="approved")
	private boolean approved;
	@Column(name="transaction_code")
	private String transactionCode;

}
