package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/book", produces = {"application/json"})
@Api(value = "Mi Bookstore API")
@CrossOrigin(origins="*")
public class BookController {

    @Autowired
    BookService bookService;



    @PostMapping
    @ApiOperation(value = "Create an instance of book")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequest bookRequest){

        return ResponseEntity.status(CREATED).body(bookService.createBook(bookRequest));
    }

    @GetMapping()
    @ApiOperation(value = "Return a list of all registered books")
    public ResponseEntity<Page<Book>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                             @RequestParam (required = false, defaultValue = "3") int size){

        return ResponseEntity.ok(bookService.getAll(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return the book according to the id")
    public ResponseEntity<Book> getById(@PathVariable Integer id){

        return ResponseEntity.ok(bookService.getById(id));
    }

    @PutMapping
    @ApiOperation(value = "Update book information")
    public ResponseEntity<Book> updateBook(@RequestParam Integer id, BookRequest bookRequest){
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete book")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }

}
