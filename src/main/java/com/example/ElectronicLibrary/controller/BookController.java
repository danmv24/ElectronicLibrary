package com.example.ElectronicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/addbook")
    public String getAddBookPage(Model model) {
        model.addAttribute("title", "Add Book");
        return "addbook";
    }
}
