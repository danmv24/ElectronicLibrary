package com.example.ElectronicLibrary.repository;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByNameAndSurname(String name, String surname);
}