package com.example.ElectronicLibrary.controller;

import com.example.ElectronicLibrary.entity.AuthorEntity;
import com.example.ElectronicLibrary.entity.BookEntity;
import com.example.ElectronicLibrary.mapper.BookMapper;
import com.example.ElectronicLibrary.repository.BookRepository;
import com.example.ElectronicLibrary.service.BookService;
import com.example.ElectronicLibrary.view.BookView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    private static final ObjectMapper om = new ObjectMapper();

    AuthorEntity authorOne = new AuthorEntity(1L, "Джек", "Лондон", null);
    AuthorEntity authorTwo = new AuthorEntity(2L, "Сурен", "Цормудян", null);

    BookEntity bookOne = new BookEntity(1L, "Странник по звёздам", authorOne, "skjfvnkdj");
    BookEntity bookTwo = new BookEntity(2L, "Наследие предков", authorTwo, "lnknvkd");

    @Test
    public void getAllBooks_Ok() throws Exception {
        List<BookEntity> bookEntities = new ArrayList<>(Arrays.asList(bookOne, bookTwo));

        List<BookView> bookViews = new ArrayList<>();

        for (BookEntity bookEntity : bookEntities) {
            bookViews.add(BookMapper.toView(bookEntity));
        }

        when(bookService.findAllBooks()).thenReturn(bookViews);


        mockMvc.perform(get("/api/book")
                .content(om.writeValueAsString(bookEntities))
                .contentType(MediaType.APPLICATION_JSON)
                        .header()
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Странник по звёздам"))
                .andExpect(jsonPath("$[0].authorName").value("Джек"))
                .andExpect(jsonPath("$[0].authorSurname").value("Лондон"))
                .andExpect(jsonPath("$[0].description").value("skjfvnkdj"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Наследие предков"))
                .andExpect(jsonPath("$[1].authorName").value("Сурен"))
                .andExpect(jsonPath("$[1].authorSurname").value("Цормудян"))
                .andExpect(jsonPath("$[1].description").value("lnknvkd"));
    }

}