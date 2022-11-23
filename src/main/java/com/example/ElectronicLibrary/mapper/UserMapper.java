package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.form.UserForm;

public class UserMapper {
    public static UserEntity toEntity(UserForm userForm, String password) {
        return UserEntity.builder()
                .username(userForm.getUsername())
                .password(password)
                .build();
    }
}
