package com.example.ElectronicLibrary.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class JwtView {

    private String accessToken;

    private String refreshToken;
}
