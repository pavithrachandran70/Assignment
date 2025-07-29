package com.example.libraryservice.controller;


import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.exception.LibraryNotFoundException;
import com.example.libraryservice.service.LibraryService;
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

@WebMvcTest(LibraryController.class)
public class LibraryControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService service;

    @Test
    public void testLibraryNotFoundException() throws Exception {
        Long libraryId = 1L;

        when(service.findById(libraryId))
                .thenThrow(new LibraryNotFoundException("Library with ID " + libraryId + " not found"));

        mockMvc.perform(get("/api/libraries/{id}", libraryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Library with ID 1 not found")))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}

