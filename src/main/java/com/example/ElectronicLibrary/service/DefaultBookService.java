package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.mapper.AuthorMapper;
import com.example.ElectronicLibrary.mapper.BookMapper;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultBookService implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Override
    public Long save(BookForm bookForm, MultipartFile file) throws IOException {

        Optional<AuthorEntity> author = authorRepository.findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname());
        if (author.isPresent()) {
            return bookRepository.save(BookMapper.toEntity(bookForm, file.getBytes(), author.get())).getId();
        } else {
            AuthorEntity authorEntity = authorRepository.save(AuthorMapper.toEntity(bookForm));
            return bookRepository.save(BookMapper.toEntity(bookForm, file.getBytes(), authorEntity)).getId();
        }

    }
}
