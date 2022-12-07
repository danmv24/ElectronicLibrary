package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.view.JwtView;

import java.util.List;

public interface AuthService {

    void create(UserForm userForm);
    JwtView authenticateUser(UserForm userForm);

    List<UserEntity> all();
}
