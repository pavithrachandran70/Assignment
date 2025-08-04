package com.example.libraryservice.dto;



import java.util.List;

import java.util.Objects;

public class LibraryDto {

    private Long id;
    private String name;
    private String city;
    private List<BookDto> books;

    public LibraryDto() {
    }


    public LibraryDto(Long id, String name, String city, List<BookDto> books) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.books = books;
    }


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

}
