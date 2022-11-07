package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.view.BookView;

import java.util.List;

public interface AuthorService {
    List<BookView> findAllBooksByAuthor(Long id);
}
