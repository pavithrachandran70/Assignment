package com.example.libraryservice.controller;


import com.example.libraryservice.dto.LibraryDto;
import com.example.libraryservice.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryControllerTest {


   @InjectMocks
   private LibraryController controller;

    @Mock
    private LibraryService service;

    private LibraryDto sampleLibraryDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleLibraryDto = new LibraryDto(1L, "Central", "Bangalore", List.of());
    }

    @Test
    void testCreate() {
        when(service.create(any(LibraryDto.class))).thenReturn(sampleLibraryDto);

        ResponseEntity<LibraryDto> response = controller.create(sampleLibraryDto);
        LibraryDto result = response.getBody();

        assertNotNull(result);
        assertEquals("Central", result.getName());
        verify(service).create(sampleLibraryDto);
    }

    @Test
    void testGetAll() {
        when(service.findAll()).thenReturn(List.of(sampleLibraryDto));

        ResponseEntity<List<LibraryDto>> response = controller.getAll();
        List<LibraryDto> result = response.getBody();

        assertEquals(1, result.size());
        assertEquals("Central", result.get(0).getName());
        verify(service).findAll();
    }

    @Test
    void testGetById() {
        when(service.findById(1L)).thenReturn(sampleLibraryDto);

        ResponseEntity<LibraryDto> response = controller.getById(1L);
        LibraryDto result = response.getBody();

        assertEquals("Central", result.getName());
        verify(service).findById(1L);
    }

    @Test
    void testUpdate() {
        LibraryDto updatedDto = new LibraryDto(1L, "Updated", "Mumbai", List.of());
        when(service.updateById(eq(1L), any(LibraryDto.class))).thenReturn(updatedDto);

        ResponseEntity<LibraryDto> response = controller.updateById(1L, updatedDto);
        LibraryDto result = response.getBody();

        assertEquals("Updated", result.getName());
        verify(service).updateById(1L, updatedDto);
    }

    @Test
    void testDelete() {
        doNothing().when(service).deleteById(1L);

        ResponseEntity<Void> response = controller.deleteById(1L);

        assertEquals(204, response.getStatusCodeValue()); // 204 No Content
        verify(service).deleteById(1L);
    }

    @Test
    void testGetWithBooks() {
        when(service.getLibraryWithBooks(1L)).thenReturn(sampleLibraryDto);

        ResponseEntity<LibraryDto> response = controller.getWithBooks(1L);
        LibraryDto result = response.getBody();

        assertEquals("Central", result.getName());
        verify(service).getLibraryWithBooks(1L);
    }
}