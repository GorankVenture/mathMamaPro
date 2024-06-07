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


@Getter
@Setter

@Entity
@Table(name="payment_details")
public class PaymentEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="my_order_id")
	private Long myOrderId;
	@Column(name="order_id")
	private String orderId;
	@Column(name="payment_id")
	private String paymentId;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@Column(name="status")
	private String status;
	@Column(name="attempts")
	private String attempts;

}
