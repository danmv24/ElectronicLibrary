package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.form.BookForm;

public class AuthorMapper {

    public static AuthorEntity toEntity(BookForm bookForm) {
        return AuthorEntity.builder()
                .name(bookForm.getAuthorName())
                .surname(bookForm.getAuthorSurname())
                .build();
    }
}
