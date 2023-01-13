package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.UserEntity;
import com.example.ElectronicLibrary.enums.Role;
import com.example.ElectronicLibrary.exception.RoleNotFoundException;
import com.example.ElectronicLibrary.exception.UserNotFoundException;
import com.example.ElectronicLibrary.mapper.UserMapper;
import com.example.ElectronicLibrary.repository.UserRepository;
import com.example.ElectronicLibrary.service.UserService;
import com.example.ElectronicLibrary.view.UserView;
import liquibase.repackaged.org.apache.commons.lang3.EnumUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public void changeRole(Long id, String roleName) {
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User with id " +id+ " was not found!");
        }

        if (EnumUtils.isValidEnumIgnoreCase(Role.class, roleName)) {
            user.get().setRole(roleName);
            userRepository.save(user.get());
        } else {
            throw new RoleNotFoundException(HttpStatus.BAD_REQUEST, "Role with name " +roleName+ " was not found!");
        }


    }

    @Override
    public List<UserView> all() {
        List<UserEntity> userEntities = userRepository.findAll();

        List<UserView> userViews = new ArrayList<>();

        for (UserEntity user : userEntities) {
            userViews.add(UserMapper.toView(user));
        }

        return userViews;
    }
}
