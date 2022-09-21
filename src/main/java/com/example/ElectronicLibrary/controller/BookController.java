package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class BookController {
    @GetMapping("/addbook")
    public String getAddBookPage(Model model) {
        model.addAttribute("title", "Add Book");
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam String title, @RequestParam String name, @RequestParam File file) throws IOException {
        FileInputStream finishFile = new FileInputStream(file);
        byte[] arr = new byte[(int)file.length()];
        finishFile.read(arr);
        finishFile.close();

        Book book = new Book(title, name, arr);

        return "redirect:/home";
    }
}
