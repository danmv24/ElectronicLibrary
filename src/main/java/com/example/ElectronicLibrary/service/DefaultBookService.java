package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.mapper.BookMapper;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DefaultBookService implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Override
    public Long save(BookForm bookForm, MultipartFile file) {
        try {
            authorRepository.findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname());
            bookRepository.save(BookMapper.toEntity(bookForm, file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bookForm.getId();
    }
}
