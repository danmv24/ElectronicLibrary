package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.service.AuthService;
import com.example.ElectronicLibrary.view.JwtView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestPart("user") UserForm userForm) {
        authService.create(userForm);
    }

    @PostMapping("/login")
    public JwtView login(@RequestPart("user") UserForm userForm) {
        return authService.authenticateUser(userForm);
    }

    @PostMapping("/token/refresh")
    public JwtView refreshToken(String refresh) {
        return authService.refresh(refresh);
    }

}
