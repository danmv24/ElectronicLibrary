package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.view.BookView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    Long save(BookForm bookForm, MultipartFile file) throws IOException;

    BookView findBook(Long id);

}
