package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultBookService implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(BookForm bookForm, MultipartFile file) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookForm.getTitle());
        bookEntity.setDescription(bookForm.getDescription());
    }
}
