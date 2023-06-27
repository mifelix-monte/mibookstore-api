package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequest bookRequest){

        return ResponseEntity.status(CREATED).body(bookService.createBook(bookRequest));
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                             @RequestParam (required = false, defaultValue = "3") int size){

        return ResponseEntity.ok(bookService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id){

        return ResponseEntity.ok(bookService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestParam Integer id, BookRequest bookRequest){
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

}
