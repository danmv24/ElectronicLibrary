package com.example.ElectronicLibrary.form;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import lombok.Data;

@Data
public class BookForm {

    private Long id;

    private String title;

    private AuthorEntity author;

    private String description;

    private byte[] data;
}
