package com.ecom.moonlight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.moonlight.entity.LoginCredential;
@Repository
public interface UserRepo extends JpaRepository<LoginCredential,String>{

    
}
