package com.example.ElectronicLibrary.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
public class RsaProperties {

    @Value("${rsa.private-key}")
    private RSAPrivateKey privateKey;

    @Value("${rsa.public-key}")
    private RSAPublicKey publicKey;
}