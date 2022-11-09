package com.example.ElectronicLibrary.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//
//    private static final ObjectMapper om = new ObjectMapper();
//
//    @Test
//    public void find_allBook_OK() throws Exception {
//        AuthorEntity author = new AuthorEntity().builder()
//                .id(1L)
//                .name("Джек")
//                .surname("Лондон")
//                .build();
//
//
//        List<BookEntity> books = Arrays.asList(
//                new BookEntity(1L, "Book A", author,"dfvdfvdf" ),
//                new BookEntity(2L, "Book B", author, "lknkjnlkj"));
//
//        when(bookRepository.findAll()).thenReturn(books);
//
//        mockMvc.perform(get("/api/author/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Book A"))
//                .andExpect(jsonPath("$.authorName").value("Джек"))
//                .andExpect(jsonPath("$.authorSurname").value("Лондон"))
//                .andExpect(jsonPath("$.description").value("dfvdfvdf"))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$.id").value(2))
//                .andExpect(jsonPath("$.name").value("Book B"))
//                .andExpect(jsonPath("$.authorName").value("Джек"))
//                .andExpect(jsonPath("$.authorSurname").value("Лондон"))
//                .andExpect(jsonPath("$.description").value("lknkjnlkj"));
//
//        verify(bookRepository, times(1)).findAll();
//    }
}