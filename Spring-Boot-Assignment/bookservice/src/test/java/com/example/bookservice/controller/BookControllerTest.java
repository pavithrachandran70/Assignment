package com.example.bookservice.controller;



import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(bookService.save(any())).thenReturn(sampleBookDTO);

        BookDTO result = bookController.create(new com.example.bookservice.entity.Book());

        assertEquals("Java Basics", result.getTitle());
        verify(bookService).save(any());
    }

    @Test
    void testGetAllBooks() {
        List<BookDTO> books = Arrays.asList(sampleBookDTO);
        when(bookService.findAll()).thenReturn(books);

        List<BookDTO> result = bookController.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookService.findById(1L)).thenReturn(sampleBookDTO);

        BookDTO result = bookController.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testUpdateBookById() {
        when(bookService.updateById(eq(1L), any())).thenReturn(sampleBookDTO);

        BookDTO result = bookController.updateById(1L, new com.example.bookservice.entity.Book());

        assertNotNull(result);
        assertEquals("Java Basics", result.getTitle());
        verify(bookService).updateById(eq(1L), any());
    }

    @Test
    void testDeleteBookById() {
        doNothing().when(bookService).deleteById(1L);

        bookController.deleteById(1L);

        verify(bookService).deleteById(1L);
    }

    @Test
    void testGetBooksByLibraryId() {
        List<BookDTO> books = List.of(sampleBookDTO);
        when(bookService.getBooksByLibraryId(10L)).thenReturn(books);

        List<BookDTO> result = bookController.getBooksByLibraryId(10L);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getLibraryId());
    }

    @Test
    void testSearchBooksByTitleAndPrice() {
        List<BookDTO> books = List.of(sampleBookDTO);
        when(bookService.findByTitleAndPriceRange("Java", 100.0, 600.0)).thenReturn(books);

        List<BookDTO> result = bookController.searchBooksByTitleAndPrice("Java", 100.0, 600.0);

        assertEquals(1, result.size());
        assertEquals("Java Basics", result.get(0).getTitle());
    }

    @Test
    void testSearchBookByTitleAndAuthor() {
        when(bookService.findByTitleAndAuthor("Java Basics", "Author A")).thenReturn(sampleBookDTO);

        BookDTO result = bookController.searchBookByTitleAndAuthor("Java Basics", "Author A");

        assertEquals("Author A", result.getAuthor());
        assertEquals("Java Basics", result.getTitle());
    }

    @Test
    void testSearchBooksByTitleAndPrice_WhenMinOrMaxPriceIsNull_ShouldThrowException() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () ->
                bookController.searchBooksByTitleAndPrice("Java", null, 500.0)
        );
        assertEquals("Both minPrice and maxPrice are required.", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () ->
                bookController.searchBooksByTitleAndPrice("Java", 100.0, null)
        );
        assertEquals("Both minPrice and maxPrice are required.", exception2.getMessage());
    }

}
