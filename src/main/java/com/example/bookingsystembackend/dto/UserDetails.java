package com.example.bookingsystembackend.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;


public interface UserDetails extends Serializable {

Collection<? extends GrantedAuthority> getAuthorities();

String getMail();
String getPassword();

boolean isAccountNonExpired();

boolean isAccountNonLocked();

boolean isCredentialsNonExpired();

boolean isEnabled();
}
