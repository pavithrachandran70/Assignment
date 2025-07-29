package com.example.bookservice.exception;


import com.example.bookservice.controller.BookController;
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

@WebMvcTest(controllers = BookController.class)
public class GlobalHandlerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    public void testHandleBookNotFoundException() throws Exception {
        Long bookId = 200L;

        when(service.findById(bookId))
                .thenThrow(new BookNotFoundException("Book with ID " + bookId + " not found"));

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Book with ID 200 not found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void testHandleGenericException() throws Exception {
        Long bookId = 300L;

        when(service.findById(bookId))
                .thenThrow(new RuntimeException("Unexpected failure"));

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", is(500)))
                .andExpect(jsonPath("$.message", containsString("Unexpected failure")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}

