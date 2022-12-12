package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.exception.AuthException;
import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.mapper.UserMapper;
import com.example.ElectronicLibrary.repository.UserRepository;
import com.example.ElectronicLibrary.service.AuthService;
import com.example.ElectronicLibrary.service.TokenService;
import com.example.ElectronicLibrary.view.JwtView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultAuthService implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final DefaultUserDetailsService userDetailsService;

    @Override
    public void create(UserForm userForm) {
        if (userRepository.existsByUsername(userForm.getUsername()))
            throw new AuthException(HttpStatus.BAD_REQUEST, "User with username "+userForm.getUsername()+
                    " already exists");

        userRepository.save(UserMapper.toEntity(userForm, passwordEncoder.encode(userForm.getPassword())));
    }

    @Override
    public JwtView authenticateUser(UserForm userForm) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        DefaultUserDetails userDetails = (DefaultUserDetails) auth.getPrincipal();

        String accessToken = tokenService.generateAccessToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails);

        return new JwtView(accessToken, refreshToken);
    }

    @Override
    public JwtView refresh(String refresh) {
        String username = tokenService.parseToken(refresh);
        DefaultUserDetails userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername(username);
        String accessToken = tokenService.generateAccessToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails);

        return new JwtView(accessToken, refreshToken);
    }

    @Override
    public List<UserEntity> all() {
        return (List<UserEntity>) userRepository.findAll();
    }
}
