package com.ecom.moonlight.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.moonlight.entity.CustomerPayment;

public interface CustomerPaymentRepo extends JpaRepository<CustomerPayment, UUID> {


}
