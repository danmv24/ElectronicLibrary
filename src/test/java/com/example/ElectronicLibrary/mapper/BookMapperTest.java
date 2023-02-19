package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.view.BookView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMapperTest {

    @Test
    void testFormToEntity() {
        BookForm bookForm = new BookForm("Финансист", "Теодор", "Драйзер", "jfnvkdf");
        AuthorEntity authorEntity = AuthorMapper.toEntity(bookForm);
        BookEntity bookEntity = BookMapper.toEntity(bookForm, authorEntity);


        assertNotNull(bookEntity);
        assertEquals(bookForm.getTitle(), bookEntity.getTitle());
        assertEquals(bookForm.getDescription(), bookEntity.getDescription());
        assertNotNull(bookEntity.getAuthor());
        assertEquals(bookForm.getAuthorName(), bookEntity.getAuthor().getName());
        assertEquals(bookForm.getAuthorSurname(), bookEntity.getAuthor().getSurname());
    }

    @Test
    void testEntityToView() {
        BookEntity bookEntity = new BookEntity(1L, "Странник по звёздам", new AuthorEntity(1L, "Джек", "Лондон", null), "skjfvnkdj");
        BookView bookView = BookMapper.toView(bookEntity);

        assertNotNull(bookView);
        assertEquals(1L, bookView.getId());
        assertEquals(bookEntity.getTitle(), bookView.getTitle());
        assertEquals(bookEntity.getDescription(), bookView.getDescription());
        assertEquals(bookEntity.getAuthor().getName(), bookView.getAuthorName());
        assertEquals(bookEntity.getAuthor().getSurname(), bookView.getAuthorSurname());
    }

}