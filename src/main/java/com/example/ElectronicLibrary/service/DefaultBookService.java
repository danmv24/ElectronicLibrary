package com.example.ElectronicLibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DefaultBookService implements BookService{

    @Override
    public void save(BookForm bookForm, MultipartFile file) {

    }
}
