package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.enums.Role;
import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.service.impl.DefaultUserDetails;
import com.example.ElectronicLibrary.view.UserView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    @Test
    void testFormToEntity() {
        UserForm userForm = UserForm.builder()
                .username("jack")
                .password("password")
                .build();

        String password = "$2a$12$L1Nn/YiNT88HNTTGPd62B.y3hSuABUfbZTG4gYRNLllj7bE8Zhnv.";
        UserEntity userEntity = UserMapper.toEntity(userForm, password);

        assertNotNull(userEntity);
        assertEquals(userForm.getUsername(), userEntity.getUsername());
        assertEquals(password, userEntity.getPassword());
        assertEquals(String.valueOf(Role.ROLE_USER), userEntity.getRole());
    }

    @Test
    void testEntityToView() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("John")
                .password("password")
                .role(String.valueOf(Role.ROLE_USER))
                .build();

        UserView userView = UserMapper.toView(userEntity);

        assertNotNull(userView);
        assertEquals(userEntity.getId(), userView.getId());
        assertEquals(userEntity.getUsername(), userView.getUsername());
        assertEquals(userEntity.getRole(), userView.getRole());

    }

    @Test
    void testEntityToUserDetails() {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("jack")
                .password("password")
                .role(String.valueOf(Role.ROLE_USER))
                .build();

        DefaultUserDetails userDetails = UserMapper.toUserDetails(userEntity);

        assertNotNull(userDetails);
        assertEquals(userEntity.getId(), userDetails.getId());
        assertEquals(userEntity.getUsername(), userDetails.getUsername());
        assertEquals(userEntity.getPassword(), userDetails.getPassword());
        assertEquals(userEntity.getRole(), userDetails.getRole());
    }
}