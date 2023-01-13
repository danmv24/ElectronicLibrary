package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.form.UserForm;
import com.example.ElectronicLibrary.view.JwtView;

public interface AuthService {

    void create(UserForm userForm);

    JwtView authenticateUser(UserForm userForm);

    JwtView refresh(String request);
}
