package com.example.ElectronicLibrary.controller;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.service.BookService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public Long addBook(@RequestPart("book")  BookForm bookForm, @RequestPart("file") MultipartFile file) {
        return bookService.save(bookForm, file);
    }

    @GetMapping("/{id}")
    public BookView getBookInformation(@PathVariable(value = "id") Long id) {
        return bookService.findBook(id);
    }

    @GetMapping("/{bookname}/download")
    public void getBook(@PathVariable(value = "bookname") String bookName) {
        bookService.downloadBook(bookName);
    }

    @PutMapping("/{book_id}/edit")
    public void editBookInformation(@PathVariable(value = "book_id") Long bookId,
                                    @RequestPart("book") BookForm bookForm) {
        bookService.edit(bookId, bookForm);
    }

    @DeleteMapping("/{book_id}/{bookname}/delete")
    public void deleteBook(@PathVariable(value = "book_id") Long bookId,
                           @PathVariable(value = "bookname") String bookName) {
        bookService.delete(bookId, bookName);
    }

}