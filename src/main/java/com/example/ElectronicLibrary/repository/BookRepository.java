package com.example.ElectronicLibrary.repository;

import com.example.ElectronicLibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
