package com.gcs.t2excelprocessorapi.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String generateToken(String username, String rol);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    }
