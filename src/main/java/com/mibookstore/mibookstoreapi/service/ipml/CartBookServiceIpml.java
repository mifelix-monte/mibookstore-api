package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.CartBook;
import com.mibookstore.mibookstoreapi.repository.BookRepository;
import com.mibookstore.mibookstoreapi.repository.CartRepository;
import com.mibookstore.mibookstoreapi.service.CartBookService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartBookServiceIpml implements CartBookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public CartBook getBookQuantity(Integer cartId, Integer bookId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        return null;
    }
}
