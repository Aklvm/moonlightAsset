package com.ecom.moonlight.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity @Component
public class LoginCredential implements UserDetails{
  @NotEmpty @NotNull @Id @Column(updatable=false)
  private String userId;
  private String password;
  private String role;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;
  private boolean accountNonExpired =true;
  private  boolean accountNonLocked=true;
  private boolean enabled=true;
  private boolean credentialExpired=false;
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth =new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role));
        return auth;
       

  }
  @Override
  public String getUsername() {
       return this.userId;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.accountNonExpired;
 }

 public boolean isAccountNonLocked() {
   
    return accountNonLocked;
 }

 public boolean isCredentialsNonExpired() {
    return credentialExpired;
 }

 public boolean isEnabled() {
    return enabled;
 }


    
}
