package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.service.AuthorService;
import com.example.ElectronicLibrary.view.BookView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public List<BookView> getAllBooksByAuthor(@PathVariable(value = "id") Long id) {
        return authorService.findAllBooksByAuthor(id);
    }
}
