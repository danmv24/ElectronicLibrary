package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.view.BookView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private DefaultAuthorService authorService;

    @Test
    void testFindBookByAuthor() {
        AuthorEntity authorEntity = AuthorEntity.builder()
                .id(1L)
                .name("Теодор")
                .surname("Драйзер")
                .build();
        BookEntity bookEntityOne = BookEntity.builder()
                .id(1L)
                .title("Финансист")
                .author(authorEntity)
                .description("kjfnvk")
                .build();

        BookEntity bookEntityTwo = BookEntity.builder()
                .id(2L)
                .title("Титан")
                .author(authorEntity)
                .description("aaakjnfv")
                .build();

        List<BookEntity> bookEntities = new ArrayList<>();
        bookEntities.add(bookEntityOne);
        bookEntities.add(bookEntityTwo);

        authorEntity.setBooks(bookEntities);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorEntity));

        List<BookView> bookViews = authorService.findAllBooksByAuthor(1L);

        assertEquals(2, bookViews.size());
        assertThat(bookViews).extracting(BookView::getTitle).contains("Финансист", "Титан");
        assertThat(bookViews).extracting(BookView::getId).contains(1L, 2L);
        assertThat(bookViews).extracting(BookView::getDescription).contains("kjfnvk", "aaakjnfv");
        assertThat(bookViews).extracting(BookView::getAuthorName).contains("Теодор");
        assertThat(bookViews).extracting(BookView::getAuthorSurname).contains("Драйзер");
    }

}