package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;

public class BookMapper {

    public static BookEntity toEntity(BookForm bookForm, byte[] data, AuthorEntity author) {
        return BookEntity.builder()
                .title(bookForm.getTitle())
                .description(bookForm.getDescription())
                .author(author)
                .data(data)
                .build();
    }
}
