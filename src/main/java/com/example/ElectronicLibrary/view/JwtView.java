package com.example.ElectronicLibrary.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtView {

    private String accessToke;

    private String refreshToken;
}
