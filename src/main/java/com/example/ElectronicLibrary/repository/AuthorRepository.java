package com.example.ElectronicLibrary.repository;

import com.example.ElectronicLibrary.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
