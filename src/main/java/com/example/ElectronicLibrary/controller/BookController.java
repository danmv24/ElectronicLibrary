package com.example.ElectronicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
public class BookController {
    @GetMapping("/addbook")
    public String getAddBookPage(Model model) {
        model.addAttribute("title", "Add Book");
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam String name, @RequestParam String title, @RequestParam File file) {

    }
}
