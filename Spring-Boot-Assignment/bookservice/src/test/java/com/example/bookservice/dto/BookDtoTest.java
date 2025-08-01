package com.example.bookservice.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookDtoTest {

    @Test
    void testGettersAndSetters() {
        BookDTO book = new BookDTO();

        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(450.0);
        book.setLibraryId(100L);

        assertEquals(1L, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(450.0, book.getPrice());
        assertEquals(100L, book.getLibraryId());
    }
}
