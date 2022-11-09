package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.view.BookView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    List<BookView> findAllBooks();
    Long save(BookForm bookForm, MultipartFile file);

    BookView findBook(Long id);

    void downloadBook(String filename);

    void edit(Long bookId, BookForm bookForm);

    void delete(Long bookId, String filename);

}
