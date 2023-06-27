package com.mibookstore.mibookstoreapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartBookIdTest {

    @InjectMocks
    CartBookId cartBookId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartBookId.setBookId(1);
        cartBookId.setCartId(21);
    }

    @Test
    void getCartId() {
        assertNotNull(cartBookId.getCartId());
    }

    @Test
    void getBookId() {
        assertNotNull(cartBookId.getBookId());
    }
}