package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.enums.Role;
import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.service.impl.DefaultUserDetails;


public class UserMapper {

    public static UserEntity toEntity(UserForm userForm, String password) {
        return UserEntity.builder()
                .username(userForm.getUsername())
                .password(password)
                .role(String.valueOf(Role.ROLE_USER))
                .build();
    }

    public static DefaultUserDetails toUserDetails(UserEntity user) {
        return DefaultUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

}
