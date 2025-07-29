package com.example.libraryservice.exception;


import com.example.libraryservice.controller.LibraryController;
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

@WebMvcTest(controllers = LibraryController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService service;

    @Test
    public void testHandleGenericException() throws Exception {
        Long id = 2L;

        when(service.findById(id)).thenThrow(new RuntimeException("Unexpected Error"));

        mockMvc.perform(get("/api/libraries/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status", is(500)))
                .andExpect(jsonPath("$.message", containsString("Unexpected Error")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }
}
