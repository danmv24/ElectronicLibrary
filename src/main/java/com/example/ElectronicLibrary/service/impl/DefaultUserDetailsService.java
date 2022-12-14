package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " +username+" not found!"));

        return DefaultUserDetails.build(user);
    }
}
