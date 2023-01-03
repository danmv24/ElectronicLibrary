package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/role/change/{id}")
    public void changeUserRole(@PathVariable(value = "id") Long id, String roleName) {
        userService.changeRole(id, roleName);
    }

}
