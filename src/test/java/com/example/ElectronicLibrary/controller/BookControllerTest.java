package com.example.ElectronicLibrary.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    private static final ObjectMapper om = new ObjectMapper();
//
//
//    @Test
//    public void save_Book_OK() throws Exception {
//
//        BookForm bookForm = new BookForm(1L, "Наследие предков", "Сурен", "Цормудян", "djkfvnlskd");
//
//        AuthorEntity author = AuthorMapper.toEntity(bookForm);
//        BookEntity book = BookMapper.toEntity(bookForm, author);
//
//        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);
//
//        mockMvc.perform(post("/api/book/add")
//                .content(om.writeValueAsString(book))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Наследие предков"))
//                .andExpect(jsonPath("$.authorName").value("Сурен"))
//                .andExpect(jsonPath("$.authorSurname").value("Цормудян"))
//                .andExpect(jsonPath("$.description").value("djkfvnlskd"));
//
//        verify(bookRepository, times(1)).save(any(BookEntity.class));
//
//    }
//
//    @Test
//    public void getBook_Ok() {
//
//    }

}