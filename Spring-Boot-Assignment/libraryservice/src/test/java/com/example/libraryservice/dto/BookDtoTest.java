package com.example.libraryservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDtoTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        BookDto book = new BookDto(101L, "Java", "Author X", 499.99);

        assertEquals(101L, book.getId());
        assertEquals("Java", book.getTitle());
        assertEquals("Author X", book.getAuthor());
        assertEquals(499.99, book.getPrice());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        BookDto book = new BookDto();
        book.setId(102L);
        book.setTitle("Spring Boot");
        book.setAuthor("Author Y");
        book.setPrice(299.99);

        assertEquals(102L, book.getId());
        assertEquals("Spring Boot", book.getTitle());
        assertEquals("Author Y", book.getAuthor());
        assertEquals(299.99, book.getPrice());
    }

    @Test
    void testEqualsAndHashCode() {
        BookDto b1 = new BookDto(1L, "ABC", "X", 100.0);
        BookDto b2 = new BookDto(1L, "ABC", "X", 100.0);

        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }
    @Test
    void testToString() {
        BookDto dto = new BookDto();
        assertNotNull(dto.toString());
    }
}
