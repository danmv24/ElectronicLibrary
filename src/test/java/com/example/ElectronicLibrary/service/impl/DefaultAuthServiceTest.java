package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.exception.UserAlreadyExistsException;
import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.repository.UserRepository;
import com.example.ElectronicLibrary.service.TokenService;
import com.example.ElectronicLibrary.view.JwtView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultAuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;


    private DefaultUserDetails defaultUserDetails;

    @Mock
    private Authentication authentication;

    @Mock
    private DefaultUserDetailsService userDetailsService;

    private UserForm userForm;

    @InjectMocks
    private DefaultAuthService authService;

    @BeforeEach
    public void setUp() {
        userForm = UserForm.builder()
                .username("username")
                .password("password")
                .build();

        defaultUserDetails = DefaultUserDetails.builder()
                .id(1L)
                .username("username")
                .password("password")
                .role("ROLE_USER")
                .build();
    }




    @Test
    void testCreateUserOk() {
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encode_password");

        authService.create(userForm);


        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void testCreateUserWhenUserAlreadyExists() {
        when(userRepository.existsByUsername(userForm.getUsername())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {
            authService.create(userForm);
        });

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword()))).thenReturn(mock(Authentication.class));
    }

    @Test
    void testAuthenticateUser() {
        String accessToken = "test_access_token";
        String refreshToken = "test_refresh_token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(defaultUserDetails);
        when(tokenService.generateAccessToken(defaultUserDetails)).thenReturn(accessToken);
        when(tokenService.generateRefreshToken(defaultUserDetails)).thenReturn(refreshToken);

        JwtView jwtView = authService.authenticateUser(userForm);

        assertEquals(accessToken, jwtView.getAccessToken());
        assertEquals(refreshToken, jwtView.getRefreshToken());
    }

    @Test
    void testRefresh() {
        String username = "username";
        String refreshToken = "test_refresh_token";
        String newRefreshToken = "new_refresh_token";
        String newAccessToken = "new_access_token";

        when(tokenService.parseToken(refreshToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(defaultUserDetails);
        when(tokenService.generateRefreshToken(defaultUserDetails)).thenReturn(newRefreshToken);
        when(tokenService.generateAccessToken(defaultUserDetails)).thenReturn(newAccessToken);

        JwtView result = authService.refresh(refreshToken);

        assertThat(result).isNotNull();
        assertEquals(result.getAccessToken(), newAccessToken);
        assertEquals(result.getRefreshToken(), newRefreshToken);
    }

}