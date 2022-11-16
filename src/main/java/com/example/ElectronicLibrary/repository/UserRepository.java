package com.example.ElectronicLibrary.repository;

import com.example.ElectronicLibrary.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
