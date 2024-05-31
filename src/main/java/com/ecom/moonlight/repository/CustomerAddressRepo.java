package com.ecom.moonlight.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.moonlight.entity.CustomerAddress;


@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, UUID> {

}
