package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.service.DefaultBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
public class BookController {

    private final DefaultBookService defaultBookService;

    @PostMapping("/add")
    public void addBook(@RequestBody BookForm bookForm, MultipartFile file) {
        defaultBookService.save(bookForm, file);
    }
}
