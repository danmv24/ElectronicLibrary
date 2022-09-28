package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.form.BookForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    Long save(BookForm bookForm, MultipartFile file) throws IOException;
}
