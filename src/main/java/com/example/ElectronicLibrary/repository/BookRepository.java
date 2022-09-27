package com.example.ElectronicLibrary.repository;

import com.example.ElectronicLibrary.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
