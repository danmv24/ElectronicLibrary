package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.view.UserView;

import java.util.List;

public interface UserService {
    void changeRole(Long id, String roleName);

    List<UserView> all();
}
