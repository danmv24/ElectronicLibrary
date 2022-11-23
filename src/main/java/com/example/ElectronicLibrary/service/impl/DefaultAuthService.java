package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.mapper.UserMapper;
import com.example.ElectronicLibrary.repository.UserRepository;
import com.example.ElectronicLibrary.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
public class DefaultAuthService implements AuthService {

//    private final TokenService tokenService;
    private final AuthenticationManager authManager;
//    private final DefaultUserDetailsService defaultUserDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void create(UserForm userForm) {
        userRepository.save(UserMapper.toEntity(userForm, passwordEncoder.encode(userForm.getPassword())));
    }

//    @Override
//    public JwtView login(UserForm userForm) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword());
//        Authentication auth = authManager.authenticate(authenticationToken);
//
//        DefaultUserDetails user = (DefaultUserDetails) defaultUserDetailsService.loadUserByUsername(userForm.getUsername());
//        String accessToken = tokenService.generateAccessToken(user);
//        String refreshToken = tokenService.generateRefreshToken(user);
//
//        return new JwtView(accessToken, refreshToken);
//    }
}
