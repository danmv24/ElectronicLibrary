package com.example.ElectronicLibrary.mapper;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.form.BookForm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorMapperTest {

    @Test
    void toEntityTest() {
        String authorName = "John";
        String authorSurname = "Doe";
        BookForm bookForm = new BookForm();
        bookForm.setAuthorName(authorName);
        bookForm.setAuthorSurname(authorSurname);

        AuthorEntity authorEntity = AuthorMapper.toEntity(bookForm);

        assertNotNull(authorEntity);
        assertEquals(authorName, authorEntity.getName());
        assertEquals(authorSurname, authorEntity.getSurname());
    }

}