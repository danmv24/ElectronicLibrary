package com.example.ElectronicLibrary.controller;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.service.DefaultBookService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final DefaultBookService defaultBookService;

    @PostMapping("/add")
    public void addBook(@RequestPart("book")  BookForm bookForm, @RequestPart("file") MultipartFile file) {
        try {
            defaultBookService.save(bookForm, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/{id}")
    public BookView getBook(@PathVariable(value = "id") Long id) {
        return defaultBookService.findBook(id);
    }
}