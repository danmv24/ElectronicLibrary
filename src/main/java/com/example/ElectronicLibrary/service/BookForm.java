package com.example.ElectronicLibrary.service;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import lombok.Data;

@Data
public class Book {

    private Long id;

    private String title;

    private AuthorEntity author;

    private String description;

    private byte[] data;
}
