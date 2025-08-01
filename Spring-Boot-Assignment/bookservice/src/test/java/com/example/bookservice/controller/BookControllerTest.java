package com.example.bookservice.controller;



import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private BookDTO sampleBookDTO;

    @BeforeEach
    void setUp() {
        sampleBookDTO = new BookDTO();
        sampleBookDTO.setId(1L);
        sampleBookDTO.setTitle("Java Basics");
        sampleBookDTO.setAuthor("Author A");
        sampleBookDTO.setPrice(500.0);
        sampleBookDTO.setLibraryId(10L);
    }

    @Test
    void testCreateBook() {
        when(bookService.save(any(Book.class))).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.create(new Book());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Java Basics", response.getBody().getTitle());
        verify(bookService).save(any(Book.class));
    }

    @Test
    void testGetAllBooks() {
        when(bookService.findAll()).thenReturn(List.of(sampleBookDTO));

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetBookById() {
        when(bookService.findById(1L)).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testUpdateBookById() {
        when(bookService.updateById(eq(1L), any(Book.class))).thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.updateById(1L, new Book());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Java Basics", response.getBody().getTitle());
        verify(bookService).updateById(eq(1L), any(Book.class));
    }

    @Test
    void testDeleteBookById() {
        doNothing().when(bookService).deleteById(1L);

        ResponseEntity<Void> response = bookController.deleteById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService).deleteById(1L);
    }

    @Test
    void testGetBooksByLibraryId() {
        when(bookService.getBooksByLibraryId(10L)).thenReturn(List.of(sampleBookDTO));

        ResponseEntity<List<BookDTO>> response = bookController.getBooksByLibraryId(10L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(10L, response.getBody().get(0).getLibraryId());
    }

    @Test
    void testSearchBooksByTitleAndPrice() {
        when(bookService.findByTitleAndPriceRange("Java", 100.0, 600.0))
                .thenReturn(List.of(sampleBookDTO));

        ResponseEntity<List<BookDTO>> response = bookController.searchBooksByTitleAndPrice("Java", 100.0, 600.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Java Basics", response.getBody().get(0).getTitle());
    }

    @Test
    void testSearchBookByTitleAndAuthor() {
        when(bookService.findByTitleAndAuthor("Java Basics", "Author A"))
                .thenReturn(sampleBookDTO);

        ResponseEntity<BookDTO> response = bookController.searchBookByTitleAndAuthor("Java Basics", "Author A");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Author A", response.getBody().getAuthor());
    }

    @Test
    void testSearchBooksByTitleAndPrice_WhenMinOrMaxPriceIsNull_ShouldReturnBadRequest() {
        ResponseEntity<List<BookDTO>> response1 =
                bookController.searchBooksByTitleAndPrice("Java", null, 500.0);
        assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());

        ResponseEntity<List<BookDTO>> response2 =
                bookController.searchBooksByTitleAndPrice("Java", 100.0, null);
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
    }
}
