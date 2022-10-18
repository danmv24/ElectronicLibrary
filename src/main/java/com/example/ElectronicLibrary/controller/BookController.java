package com.example.ElectronicLibrary.controller;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.service.DefaultBookService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final DefaultBookService defaultBookService;

    @PostMapping("/add")
    public Long addBook(@RequestPart("book")  BookForm bookForm, @RequestPart("file") MultipartFile file) {
        try {
            return defaultBookService.save(bookForm, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/{id}")
    public BookView getBookInformation(@PathVariable(value = "id") Long id) {
        return defaultBookService.findBook(id);
    }

    @GetMapping("/download/{bookname}")
    public void getBook(@PathVariable(value = "bookname") String bookName) {
        defaultBookService.downloadBook(bookName);
    }

}