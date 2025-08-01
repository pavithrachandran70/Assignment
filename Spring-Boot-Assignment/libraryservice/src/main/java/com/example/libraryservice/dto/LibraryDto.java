package com.example.libraryservice.dto;


import lombok.*;

import java.awt.print.Book;
import java.util.List;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class LibraryDto {
//
//
//    private Long id;
//    private String name;
//    private String city;
//    private List<BookDto> books;
//
//
//}


import java.util.List;
import java.util.Objects;

public class LibraryDto {

    private Long id;
    private String name;
    private String city;
    private List<BookDto> books;

    // No-args constructor
    public LibraryDto() {
    }

    // All-args constructor
    public LibraryDto(Long id, String name, String city, List<BookDto> books) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.books = books;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryDto)) return false;
        LibraryDto that = (LibraryDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city) &&
                Objects.equals(books, that.books);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, books);
    }

    // toString() method
    @Override
    public String toString() {
        return "LibraryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", books=" + books +
                '}';
    }
}
