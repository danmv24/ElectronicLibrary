package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.exception.BookServiceException;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.mapper.AuthorMapper;
import com.example.ElectronicLibrary.mapper.BookMapper;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.repository.BookRepository;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultBookService implements BookService {

    private final MinioService minioService;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    @Override
    public Long save(BookForm bookForm, MultipartFile file) {
        minioService.uploadFile(file);

        Optional<AuthorEntity> authorOptional = authorRepository.findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname());
        if (authorOptional.isPresent()) {
            return bookRepository.save(BookMapper.toEntity(bookForm, authorOptional.get())).getId();
        } else {
            AuthorEntity authorEntity = authorRepository.save(AuthorMapper.toEntity(bookForm));
            return bookRepository.save(BookMapper.toEntity(bookForm, authorEntity)).getId();
        }


    }

    @Override
    public BookView findBook(Long id) {
        Optional<BookEntity> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return BookMapper.toView(bookOptional.get());
        } else {
            throw new BookServiceException(HttpStatus.NOT_FOUND, "Book not found!");
        }

    }

    public void downloadBook(String filename) {
        minioService.getFile(filename);
    }

    @Override
    public void edit(Long bookId, BookForm bookForm) {
        Optional<BookEntity> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            AuthorEntity authorEntity = bookOptional.get().getAuthor();
            authorEntity.setName(bookForm.getAuthorName());
            authorEntity.setSurname(bookForm.getAuthorSurname());
            authorRepository.save(authorEntity);

            BookEntity bookEntity = bookOptional.get();
            bookEntity.setTitle(bookForm.getTitle());
            bookEntity.setDescription(bookForm.getDescription());
            bookRepository.save(bookEntity);

        } else {
            throw new BookServiceException(HttpStatus.NOT_FOUND, "Book not found!!!");
        }
    }

}
