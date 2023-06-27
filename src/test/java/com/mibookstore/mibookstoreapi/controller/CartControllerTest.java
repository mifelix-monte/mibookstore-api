package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;


@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartController;
    @Mock
    private CartService cartService;
    ClientRequest clientRequest;
    BookRequest bookRequest;
    Book book;
    Client client;
    private Cart cart;
    CartRequest cartRequest;

    @BeforeEach
    public void setup() {
        cartRequest = new CartRequest(1);
        cart = new Cart();
        book = new Book();
        client = new Client();
        bookRequest = new BookRequest("Orgulho e Preconceito", "Jane Austen", new BigDecimal("19.99"), 289, 5);
        clientRequest = new ClientRequest("Mariana Rios", "21874562588", "marios@hotmail.com", "Rua Pedra Alta, 458, Rosa", "12988541477");
    }

    @Test
    public void mustCreateCart() {
        when(cartService.addItem(client.getId(), book.getId(), cartRequest)).thenReturn(cart);
        var response = assertDoesNotThrow(() -> cartController.createCart(client.getId(), book.getId(), cartRequest));
        assertNotNull(response);
        assertEquals(ResponseEntity.status(CREATED).body(cart), response);
    }

    @Test
    public void mustReturnAllCarts() {
        var response = assertDoesNotThrow(() -> cartController.getAll(1,1));
        assertEquals(ResponseEntity.ok(cartService.getAll(1,1)), response);
    }

    @Test
    public void mustReturnCartById() {
        when(cartService.getById(cart.getId())).thenReturn(cart);
        var response = assertDoesNotThrow(() -> cartController.getById(cart.getId()));
        assertNotNull(response);
    }

    @Test
    public void mustUpdateCart() {
        when(cartService.updateCart(client.getId(), book.getId(), cartRequest)).thenReturn(cart);
        var response = assertDoesNotThrow(() -> cartController.updateCart(client.getId(), book.getId(), cartRequest));
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(cartService.updateCart(client.getId(), book.getId(), cartRequest)), response);
    }

    @Test
    public void mustClearCart() {
        when(cartService.clearCart(cart.getId())).thenReturn(cart);
        assertDoesNotThrow(() -> cartController.clearCart(cart.getId()));
    }

    @Test
    public void mustRemoveItem() {
        when(cartService.removeItem(cart.getId(), book.getId())).thenReturn(cart);
        var response = assertDoesNotThrow(() -> cartController.removeItem(client.getId(), book.getId()));
        assertNotNull(response);
        assertEquals(ResponseEntity.ok(cartService.removeItem(cart.getId(), book.getId())), response);
    }

    @Test
    public void mustDeleteCart() {
        var response = assertDoesNotThrow(() -> cartController.deleteCart(cart.getId()));
        assertEquals(ResponseEntity.noContent().build(), response);
    }
}
