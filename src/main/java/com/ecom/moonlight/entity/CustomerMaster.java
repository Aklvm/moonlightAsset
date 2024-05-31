package com.ecom.moonlight.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
@EnableJpaAuditing
public class CustomerMaster {

  @Id
  @Column(name = "cust_id")
  private String userId;
  private String firstName;
  private String lastName;
  @Column(unique = true,updatable = false)
  private String email;
  private String phone;
  @ColumnDefault("silver")
  private String segment;
  private String gender;
  @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
  private List<CustomerAddress> customerAddress;
  @OneToMany(mappedBy = "customerMaster",cascade = CascadeType.ALL)
  private List<CustomerPayment> customerPayment;
  private LocalDateTime dateOfBirth;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;

    
}
