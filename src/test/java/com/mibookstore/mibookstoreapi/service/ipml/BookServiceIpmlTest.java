package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceIpmlTest {

    @InjectMocks
    private BookServiceIpml service;

    @Mock
    private BookRepository repository;
    private Book book;
    private BookRequest bookRequest;
    private Page<Book> pageBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startBook();
    }

    @Test
    void whenCreateBookTheReturnSuccess() {
        when(repository.save(any())).thenReturn(book);

        Book response = service.createBook(bookRequest);

        assertNotNull(response);
        assertEquals(Book.class, response.getClass());
        assertEquals(book.getId(), response.getId());
        assertEquals(book.getTitle(), response.getTitle());
        assertEquals(book.getAuthor(), response.getAuthor());
        assertEquals(book.getPrice(), response.getPrice());
        assertEquals(book.getPages(), response.getPages());
        assertEquals(book.getStockQuantity(), response.getStockQuantity());
    }

    @Test
    void whenGetAllThenReturnAnListOfUsers() {
        Page<Book> books = Mockito.mock(Page.class);
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.ASC,"title");

        when(repository.findAll(pageRequest)).thenReturn(books);

        Page<Book> response = service.getAll(1,1);

        assertNotNull(response);
    }

    @Test
    void whenGetByIdThenReturnAnBookInstance() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(book));

        Book response = service.getById(book.getId());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Book.class, response.getClass());
        Assertions.assertEquals(book.getId(), response.getId());

        Mockito.verify(repository).findById(book.getId());
    }

    @Test
    void whenUpdateBookTheReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(book));
        when(repository.save(any())).thenReturn(book);

        Book response = service.updateBook(book.getId(), bookRequest);

        assertNotNull(response);
        assertEquals(Book.class, response.getClass());
        assertEquals(book.getId(), response.getId());
        assertEquals(book.getTitle(), response.getTitle());
        assertEquals(book.getAuthor(), response.getAuthor());
        assertEquals(book.getPrice(), response.getPrice());
        assertEquals(book.getPages(), response.getPages());
        assertEquals(book.getStockQuantity(), response.getStockQuantity());
    }

    @Test
    void deleteBookWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(book));
        doNothing().when(repository).delete(book);
        service.deleteBook(book.getId());
        verify(repository, times(1)).delete(book);
    }

    private void startBook() {
        book = new Book();
        book.setId(1);
        book.setTitle("O Cemitério");
        book.setAuthor("Stephen King");
        book.setPages(405);
        book.setPrice(new BigDecimal("35.99"));
        book.setStockQuantity(4);

        bookRequest = new BookRequest("O Cemitério", "Stephen King", new BigDecimal("35.99"), 405, 4);

    }
}