package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.service.BookService;
import com.mibookstore.mibookstoreapi.service.ipml.BookServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceIpml bookServiceIpml;

    @Autowired
    BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody BookRequest bookRequest){
        return bookServiceIpml.createBook(bookRequest);
    }

    @GetMapping
    public Page<Book> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                             @RequestParam (required = false, defaultValue = "3") int size){

        return bookService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id){
        return bookServiceIpml.getById(id);
    }

    @PutMapping
    public Book updateBook(@RequestParam Integer id, BookRequest bookRequest){
        return bookServiceIpml.updateBook(id, bookRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id){
        bookServiceIpml.deleteBook(id);
    }

}
