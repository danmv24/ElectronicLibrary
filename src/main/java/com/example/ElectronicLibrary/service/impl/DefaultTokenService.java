package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.service.TokenService;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
public class DefaultTokenService implements TokenService {

    private final JwtEncoder jwtEncoder;

    @Override
    public String generateAccessToken(DefaultUserDetails userDetails) {
        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    @Override
    public String generateRefreshToken(DefaultUserDetails userDetails) {
        Instant now = Instant.now();


        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(userDetails.getUsername())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    @Override
    public String parseToken(String token) {
        try {
            SignedJWT decodedJWT = SignedJWT.parse(token);
            String subject = decodedJWT.getJWTClaimsSet().getSubject();
            return subject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
