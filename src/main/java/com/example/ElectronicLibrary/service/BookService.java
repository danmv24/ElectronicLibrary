package com.example.ElectronicLibrary.service;

import org.springframework.web.multipart.MultipartFile;

public interface BookService {
    void save(BookForm bookForm, MultipartFile file);
}
