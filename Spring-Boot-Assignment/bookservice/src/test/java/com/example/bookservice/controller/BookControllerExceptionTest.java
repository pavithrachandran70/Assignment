package com.example.bookservice.controller;


import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    public void testBookNotFoundException() throws Exception {
        Long bookId = 100L;

        when(service.findById(bookId))
                .thenThrow(new BookNotFoundException("Book with ID " + bookId + " not found"));

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Book with ID 100 not found")))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}
