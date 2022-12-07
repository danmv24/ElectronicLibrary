package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.service.impl.DefaultUserDetails;

public interface TokenService {
    String generateAccessToken(DefaultUserDetails userDetails);

    String generateRefreshToken(DefaultUserDetails userDetails);

    String parseToken(String token);
}
