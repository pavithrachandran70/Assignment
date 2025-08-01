package com.example.libraryservice.dto;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LibraryDtoTest {





    @Test
    void testAllArgsConstructorAndGetters() {
        BookDto book1 = new BookDto(1L, "Book A", "Author A", 100.0);
        BookDto book2 = new BookDto(2L, "Book B", "Author B", 200.0);
        List<BookDto> books = Arrays.asList(book1, book2);

        LibraryDto dto = new LibraryDto(10L, "Central Library", "Mumbai", books);

        assertEquals(10L, dto.getId());
        assertEquals("Central Library", dto.getName());
        assertEquals("Mumbai", dto.getCity());
        assertEquals(2, dto.getBooks().size());
        assertEquals("Book A", dto.getBooks().get(0).getTitle());
        assertEquals("Author B", dto.getBooks().get(1).getAuthor());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        LibraryDto dto = new LibraryDto();
        dto.setId(11L);
        dto.setName("Local Library");
        dto.setCity("Delhi");

        BookDto book = new BookDto();
        book.setId(101L);
        book.setTitle("Java");
        book.setAuthor("Author X");
        book.setPrice(200.0);

        dto.setBooks(Collections.singletonList(book));

        assertEquals(11L, dto.getId());
        assertEquals("Local Library", dto.getName());
        assertEquals("Delhi", dto.getCity());
        assertEquals(1, dto.getBooks().size());
        assertEquals("Java", dto.getBooks().get(0).getTitle());
        assertEquals("Author X", dto.getBooks().get(0).getAuthor());
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
        LibraryDto dto = new LibraryDto(1L, "Lib", "City", null);
        String str = dto.toString();
        assertNotNull(str);
        assertTrue(str.contains("Lib"));
        assertTrue(str.contains("City"));
    }

    @Test
    void testNullBooksList() {
        LibraryDto dto = new LibraryDto(5L, "Lib", "Pune", null);
        assertNull(dto.getBooks());
    }

    @Test
    void testEqualsWithSameObject() {
        LibraryDto dto = new LibraryDto();
        assertThat(dto.equals(dto)).isTrue();
    }

    @Test
    void testEqualsWithNull() {
        LibraryDto dto = new LibraryDto();
        assertThat(dto.equals(null)).isFalse();
    }

    @Test
    void testEqualsWithDifferentClass() {
        LibraryDto dto = new LibraryDto();
        assertThat(dto.equals("Some String")).isFalse();
    }

    @Test
    void testEqualsWithDifferentValues() {
        LibraryDto dto1 = new LibraryDto(1L, "A", "City1", null);
        LibraryDto dto2 = new LibraryDto(2L, "B", "City2", null);
        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    void testEqualsWithNullFields() {
        LibraryDto dto1 = new LibraryDto(null, null, null, null);
        LibraryDto dto2 = new LibraryDto(null, null, null, null);
        assertThat(dto1).isEqualTo(dto2);
    }

    @Test
    void testEqualsWithDifferentBooks() {
        BookDto book1 = new BookDto(1L, "Book A", "Author A", 100.0);
        BookDto book2 = new BookDto(2L, "Book B", "Author B", 200.0);

        LibraryDto dto1 = new LibraryDto(1L, "Lib", "City", Collections.singletonList(book1));
        LibraryDto dto2 = new LibraryDto(1L, "Lib", "City", Collections.singletonList(book2));

        assertThat(dto1).isNotEqualTo(dto2); // different books list
    }

    @Test
    void testEqualsWithDifferentName() {
        LibraryDto l1 = new LibraryDto(1L, "Library A", "City", null);
        LibraryDto l2 = new LibraryDto(1L, "Library B", "City", null);
        assertThat(l1).isNotEqualTo(l2);
    }


    @Test
    void testEqualsWithDifferentId() {
        LibraryDto l1 = new LibraryDto(1L, "Library", "City", null);
        LibraryDto l2 = new LibraryDto(2L, "Library", "City", null);
        assertThat(l1).isNotEqualTo(l2);
    }

    @Test
    void testEqualsWithDifferentCity() {
        LibraryDto l1 = new LibraryDto(1L, "Library", "City A", null);
        LibraryDto l2 = new LibraryDto(1L, "Library", "City B", null);
        assertThat(l1).isNotEqualTo(l2);
    }

}
