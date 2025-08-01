package com.example.bookservice.controller;


import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.entity.Book;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody Book book) {
        BookDTO created = service.save(book);
        return ResponseEntity.ok(created); // or ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = service.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        BookDTO book = service.findById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateById(@PathVariable Long id, @RequestBody Book book) {
        BookDTO updated = service.updateById(id, book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<BookDTO>> getBooksByLibraryId(@PathVariable Long libraryId) {
        List<BookDTO> books = service.getBooksByLibraryId(libraryId);
        return ResponseEntity.ok(books);
    }


    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooksByTitleAndPrice(
            @RequestParam String title,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (minPrice == null || maxPrice == null) {
            return ResponseEntity.badRequest().build();
        }

        List<BookDTO> results = service.findByTitleAndPriceRange(title, minPrice, maxPrice);
        return ResponseEntity.ok(results);
    }


    @GetMapping("/search/author")
    public ResponseEntity<BookDTO> searchBookByTitleAndAuthor(
            @RequestParam String title,
            @RequestParam String author) {
        BookDTO result = service.findByTitleAndAuthor(title, author);
        return ResponseEntity.ok(result);
    }

}
