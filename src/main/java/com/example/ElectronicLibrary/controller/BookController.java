package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.service.BookService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookView> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/add")
    public Long addBook(@RequestPart("book")  BookForm bookForm, @RequestPart("file") MultipartFile file) {
        return bookService.save(bookForm, file);
    }

    @GetMapping("/{id}")
    public BookView getBookInformation(@PathVariable(value = "id") Long id) {
        return bookService.findBook(id);
    }

    @GetMapping("/download/{bookname}")
    public void getBook(@PathVariable(value = "bookname") String bookName) {
        bookService.downloadBook(bookName);
    }

    @PutMapping("/edit/{book_id}")
    public void editBookInformation(@PathVariable(value = "book_id") Long bookId,
                                    @RequestPart("book") BookForm bookForm) {
        bookService.edit(bookId, bookForm);
    }

    @DeleteMapping("/delete/{book_id}/{bookname}")
    public void deleteBook(@PathVariable(value = "book_id") Long bookId,
                           @PathVariable(value = "bookname") String bookName) {
        bookService.delete(bookId, bookName);
    }

}