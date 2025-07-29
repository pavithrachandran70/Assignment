package com.example.libraryservice.dto;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryDtoTest {
//
//    @Test
//    void testAllArgsConstructorAndGetters() {
//        BookDto book1 = new BookDto(1L, "Book A", "Author A", 100.0);
//        BookDto book2 = new BookDto(2L, "Book B", "Author B", 200.0);
//        List<BookDto> books = Arrays.asList(book1, book2);
//
//        LibraryDto dto = new LibraryDto(10L, "Central Library", "Mumbai", books);
//
//        assertEquals(10L, dto.getId());
//        assertEquals("Central Library", dto.getName());
//        assertEquals("Mumbai", dto.getCity());
//        assertEquals(2, dto.getBooks().size());
//        assertEquals("Book A", dto.getBooks().get(0).getTitle());
//    }
//
//    @Test
//    void testNoArgsConstructorAndSetters() {
//        LibraryDto dto = new LibraryDto();
//        dto.setId(11L);
//        dto.setName("Local Library");
//        dto.setCity("Delhi");
//
//        assertEquals(11L, dto.getId());
//        assertEquals("Local Library", dto.getName());
//        assertEquals("Delhi", dto.getCity());
//    }
//
//    @Test
//    void testEqualsAndHashCode() {
//        LibraryDto lib1 = new LibraryDto(1L, "Name", "City", null);
//        LibraryDto lib2 = new LibraryDto(1L, "Name", "City", null);
//
//        assertEquals(lib1, lib2);
//        assertEquals(lib1.hashCode(), lib2.hashCode());
//    }

    @Test
    void testAllArgsConstructor() {
        BookDto book = new BookDto(1L, "Title", "Author", 100.0);

        LibraryDto dto = new LibraryDto(1L, "Library", "City", Collections.singletonList(book));

        assertEquals(1L, dto.getId());
        assertEquals("Library", dto.getName());
        assertEquals("City", dto.getCity());
        assertEquals(1, dto.getBooks().size());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        LibraryDto dto = new LibraryDto();
        dto.setId(2L);
        dto.setName("My Library");
        dto.setCity("Chennai");

        BookDto book1 = new BookDto();
        book1.setId(101L);
        book1.setTitle("Java");
        book1.setAuthor("Author X");
        book1.setPrice(200.0);

        dto.setBooks(Arrays.asList(book1));

        assertEquals(2L, dto.getId());
        assertEquals("My Library", dto.getName());
        assertEquals("Chennai", dto.getCity());
        assertEquals(1, dto.getBooks().size());
    }

    @Test
    void testEqualsAndHashCode() {
        LibraryDto l1 = new LibraryDto(1L, "A", "B", null);
        LibraryDto l2 = new LibraryDto(1L, "A", "B", null);

        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testToString() {
        LibraryDto dto = new LibraryDto();
        assertNotNull(dto.toString());
    }
}
