package com.example.libraryservice.service;


import com.example.libraryservice.dto.LibraryDto;


import java.util.List;


public interface LibraryService {

    LibraryDto create(LibraryDto libraryDto);

    List<LibraryDto> findAll();

    LibraryDto findById(Long id);

    LibraryDto updateById(Long id, LibraryDto updatedLibraryDto);

    void deleteById(Long id);

    LibraryDto getLibraryWithBooks(Long id);
}
