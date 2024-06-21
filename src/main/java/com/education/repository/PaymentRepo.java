package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.PaymentEntity;

public interface PaymentRepo extends JpaRepository<PaymentEntity,Long>
{
PaymentEntity findByOrderId(String orderId);
PaymentEntity findByMobileNumber(String mobileNumber);
}
