package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import org.springframework.web.multipart.MultipartFile;

public class BookMapper {

    public static BookEntity toEntity(BookForm bookForm, byte[] data) {
        return BookEntity.builder()
                .title(bookForm.getTitle())
                .description(bookForm.getDescription())
                .data(data)
                .build();
    }
}
