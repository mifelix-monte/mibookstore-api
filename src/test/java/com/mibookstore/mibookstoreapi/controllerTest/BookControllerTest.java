package com.mibookstore.mibookstoreapi.controllerTest;

import com.mibookstore.mibookstoreapi.controller.BookController;
import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private BookRequest bookRequest;

    Book book;

    //para cada teste que eu executar, ele irá inicializar as variáveis citadas abaixo para eu usar dentro dos testes:
    @BeforeEach
    public void setup() {
        bookRequest = new BookRequest("A culpa é das Estrelas", "John Green", new BigDecimal("40.90"), 263, 5);
        book = new Book();
    }

    @Test
    public void mustCreateBook() {
        var response = assertDoesNotThrow(() -> bookController.createBook(bookRequest));
        assertEquals(ResponseEntity.status(CREATED).body(bookService.createBook(bookRequest)), response);
    }

    @Test
    public void mustUpdateBook() {
        when(bookService.updateBook(book.getId(), bookRequest)).thenReturn(book);
        var response = assertDoesNotThrow(() -> bookController.updateBook(book.getId(), bookRequest));
        assertNotNull(response);
    }

    @Test
    public void mustGetAllBooks() {
        var response = assertDoesNotThrow(() -> bookController.getAll(3, 3));
        assertNotNull(response);
    }

    @Test
    public void mustGetBookById() {
        when(bookService.getById(book.getId())).thenReturn(book);
        var response = assertDoesNotThrow(() -> bookController.getById(book.getId()));
        assertNotNull(response);
    }

    @Test
    public void mustDeletBook() {
        var response = assertDoesNotThrow(() -> bookController.deleteBook(book.getId()));
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
