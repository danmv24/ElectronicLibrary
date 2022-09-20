package com.example.ElectronicLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/addbook")
    public String getAddBookPage() {
        return "addbook";
    }
}
