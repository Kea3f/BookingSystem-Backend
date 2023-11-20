package com.example.bookingsystembackend.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public interface LoginDto extends Serializable {

Collection<? extends GrantedAuthority> getAuthorities();

String getMail();
String getPassword();

}
