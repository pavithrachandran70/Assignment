package com.example.bookservice.controller;


import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    //bookservice
    private BookService service;

    @PostMapping

    public BookDTO create(@RequestBody Book book) {
    return service.save(book);
}

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateById(@PathVariable Long id, @RequestBody Book book) {
        return service.updateById(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/library/{libraryId}")
    public List<BookDTO> getBooksByLibraryId(@PathVariable Long libraryId) {
        return service.getBooksByLibraryId(libraryId);
    }
    // 🔍 Scenario 1: Get books by title and price range
    @GetMapping("/search")
    public List<BookDTO> searchBooksByTitleAndPrice(
            @RequestParam String title,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("Both minPrice and maxPrice are required.");
        }

        return service.findByTitleAndPriceRange(title, minPrice, maxPrice);
    }

//    localhos:8092/api/books/search?title="bookname"?minPrice=100?max

    // 🔍 Scenario 2: Get a book by title and author
    @GetMapping("/search/author")
    public BookDTO searchBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String author) {
        return service.findByTitleAndAuthor(title, author);
    }

}
