package com.example.ElectronicLibrary.service.impl;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.form.BookForm;
import com.example.ElectronicLibrary.repository.AuthorRepository;
import com.example.ElectronicLibrary.repository.BookRepository;
import com.example.ElectronicLibrary.service.MinioService;
import com.example.ElectronicLibrary.view.BookView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultBookServiceTest {

    @Mock
    private MinioService minioService;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DefaultBookService bookService;

    @Mock
    private AuthorRepository authorRepository;

    private List<BookEntity> bookEntities;

    private AuthorEntity authorEntity;

    private BookEntity bookEntity;

    private BookForm bookForm;

    private MultipartFile file;

    private Long bookId;

    @BeforeEach
    public void setUp() {
        bookEntities = new ArrayList<>();
        bookEntities.add(new BookEntity(1L, "Странник по звёздам", new AuthorEntity(1L, "Джек", "Лондон", null), "skjfvnkdj"));
        bookEntities.add(new BookEntity(2L, "Наследие предков", new AuthorEntity(2L, "Сурен", "Цормудян", null), "lnknvkd"));

        authorEntity = new AuthorEntity(3L, "Теодор", "Драйзер", null);

        bookEntity = new BookEntity(3L, "Парк Юрского Периода", new AuthorEntity(4L, "Майкл", "Крайтон", null), "knfkvj");

        bookForm = new BookForm("Финансист", "Теодор", "Драйзер", "jfnvkdf");

        bookId = 3L;

        file = new MockMultipartFile("file", "book.pdf", "application/pdf", "content".getBytes());
    }

    @Test
    void findAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(bookEntities);
        List<BookView> bookViews = bookService.findAllBooks();

        assertEquals(2, bookViews.size());
        assertThat(bookViews).extracting(BookView::getTitle).contains("Странник по звёздам", "Наследие предков");
    }


    @Test
    void saveBookWhenAuthorFoundTest() {
        when(authorRepository.findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname())).thenReturn(Optional.of(authorEntity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity(1L, bookForm.getTitle(), authorEntity, bookForm.getDescription()));

        Long bookId = bookService.save(bookForm, file);

        verify(minioService).uploadFile(file);
        verify(authorRepository).findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname());
        verify(bookRepository).save(any(BookEntity.class));
        assertThat(bookId).isEqualTo(1L);

    }

    @Test
    void saveBookWhenAuthorNotFoundTest() {
        when(authorRepository.findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname())).thenReturn(Optional.empty());
        when(authorRepository.save(any(AuthorEntity.class))).thenReturn(new AuthorEntity(1L, bookForm.getAuthorName(), bookForm.getAuthorSurname(), null));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity(1L, bookForm.getTitle(), authorEntity, bookForm.getDescription()));

        Long bookId = bookService.save(bookForm, file);

        verify(minioService).uploadFile(file);
        verify(authorRepository).findByNameAndSurname(bookForm.getAuthorName(), bookForm.getAuthorSurname());
        verify(authorRepository).save(any(AuthorEntity.class));
        verify(bookRepository).save(any(BookEntity.class));
        assertThat(bookId).isEqualTo(1L);
    }

    @Test
    void findBookTest() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        BookView bookView = bookService.findBook(bookId);

        assertEquals(bookEntity.getId(), bookView.getId());
        assertEquals(bookEntity.getTitle(), bookView.getTitle());
        assertEquals(bookEntity.getDescription(), bookView.getDescription());
    }

    @Test
    void deleteBookTest() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        bookService.delete(bookId, file.getOriginalFilename());
        verify(bookRepository, times(1)).delete(bookEntity);
        verify(minioService, times(1)).deleteFile(file.getOriginalFilename());

    }

    @Test
    void editBook() {
        Long bookId = 3L;

        BookForm bookForm = BookForm.builder()
                .title("New Title")
                .authorName("New Name")
                .authorSurname("New Surname")
                .description("New desc")
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        bookService.edit(bookId, bookForm);

        assertEquals(bookForm.getTitle(), bookEntity.getTitle());
        assertEquals(bookForm.getDescription(), bookEntity.getDescription());
        assertEquals(bookForm.getAuthorName(), bookEntity.getAuthor().getName());
        assertEquals(bookForm.getAuthorSurname(), bookEntity.getAuthor().getSurname());
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    void downloadBook() {
        String filename = "example.pdf";
        bookService.downloadBook(filename);
        verify(minioService).getFile(filename);
    }

}