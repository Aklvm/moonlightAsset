package com.ecom.moonlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.moonlight.entity.CustomerMaster;

@Repository
public interface CustomerMasterRepo extends JpaRepository<CustomerMaster, String> {
    
}
