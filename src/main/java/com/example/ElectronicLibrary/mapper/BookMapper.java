package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.view.BookView;

public class BookMapper {

    public static BookEntity toEntity(BookForm bookForm, AuthorEntity author) {
        return BookEntity.builder()
                .title(bookForm.getTitle())
                .description(bookForm.getDescription())
                .author(author)
                .build();
    }

    public static BookView toView(BookEntity bookEntity) {
        return BookView.builder()
                .title(bookEntity.getTitle())
                .description(bookEntity.getDescription())
                .authorName(bookEntity.getAuthor().getName())
                .authorSurname(bookEntity.getAuthor().getSurname())
//                .link(bookEntity.getLink())
                .build();
    }
}
