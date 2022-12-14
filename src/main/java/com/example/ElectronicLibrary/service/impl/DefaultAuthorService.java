package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.exception.AuthorNotFoundException;
import com.example.ElectronicLibrary.mapper.BookMapper;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.service.AuthorService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<BookView> findAllBooksByAuthor(Long id) {
        Optional<AuthorEntity> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            AuthorEntity author = authorOptional.get();
            List<BookEntity> books = author.getBooks();

            List<BookView> bookViews = new ArrayList<>();

            for (BookEntity book : books)
                bookViews.add(BookMapper.toView(book));

            return bookViews;

        } else {
            throw new AuthorNotFoundException(HttpStatus.NOT_FOUND, "Author not found!!!");
        }

    }
}