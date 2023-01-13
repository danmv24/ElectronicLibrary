package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.service.UserService;
import com.example.ElectronicLibrary.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/role/change/{id}")
    public void changeUserRole(@PathVariable(value = "id") Long id, String roleName) {
        userService.changeRole(id, roleName);
    }

    @GetMapping("/all")
    public List<UserView> all() {
        return userService.all();
    }

}
