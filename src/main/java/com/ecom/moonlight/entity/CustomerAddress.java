package com.ecom.moonlight.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CustomerAddress {

@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID addressId;
private String addressLine1;
private String addressLine2;
private String city;
private String state;
private String country;
private String zipCode;

  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;
@ManyToOne
@JoinColumn(name= "userId",nullable = true)
private CustomerMaster customerMaster;


}
