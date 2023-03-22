package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.CartBook;
import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.repository.CartBookRepository;
import com.mibookstore.mibookstoreapi.repository.CartRepository;
import com.mibookstore.mibookstoreapi.service.BookService;
import com.mibookstore.mibookstoreapi.service.CartService;
import com.mibookstore.mibookstoreapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceIpml implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    BookService bookService;

    @Autowired
    CartBookRepository cartBookRepository;

    @Override
    public Cart addItem(Integer clientId, Integer bookId, CartRequest cartRequest) {
        Client client = clientService.getById(clientId);
        Book book = bookService.getById(bookId);

        Cart newCart = new Cart(cartRequest);
        CartBook newCartBook = new CartBook(newCart, book);
        newCart.setClient(client);
        book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
        newCart.getBooks().add(newCartBook);
        getCartAmount(newCart);
        newCartBook.setItemQuantity(cartRequest.getCartItemQuantity());
        return cartRepository.save(newCart);
    }

    @Override
    public Cart updateCart(Integer cartId, Integer bookId, CartRequest cartRequest) {
        Cart cart = getById(cartId);
        Book book = bookService.getById(bookId);
        CartBook cartBook = cartBookRepository.findByCartIdAndBookId(cartId, bookId);

        if (!(cartBook == null)) {
            book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
            cartBook.setItemQuantity(cartBook.getItemQuantity() + cartRequest.getCartItemQuantity());
            setCartAmount(cart, cartBook);
        } else {
            CartBook newCartBook = new CartBook(cart, book);
            book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
            newCartBook.setItemQuantity(cartRequest.getCartItemQuantity());
            cart.getBooks().add(newCartBook);
            setCartAmount(cart, newCartBook);
        }

        cart.setCartItemQuantity(cart.getCartItemQuantity() + cartRequest.getCartItemQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getById(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public Cart removeItem(Integer cartId, Integer bookId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Book book = bookService.getById(bookId);
        CartBook cartBook = cartBookRepository.findByCartIdAndBookId(cartId, bookId);

        if (cartBook.getItemQuantity() > 1) {
            cartBook.setItemQuantity(cartBook.getItemQuantity() - 1);
        } else if (cartBook.getItemQuantity() == 1) {
            cart.getBooks().remove(cartBook);
        }

        book.setStockQuantity(book.getStockQuantity() + cart.getCartItemQuantity());
        cart.setCartItemQuantity(cart.getCartItemQuantity() - 1);
        cart.setAmount(cart.getAmount().subtract(book.getPrice()));

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();

        for (CartBook item : cart.getBooks()) {
            item.getBook().setStockQuantity(item.getBook().getStockQuantity() + item.getItemQuantity());
        }
        cartRepository.delete(cart);
    }

    private void getCartAmount(Cart cart) {
        BigDecimal amountPrice = new BigDecimal(0);

        for (CartBook cartBook : cart.getBooks()) {
            BigDecimal amountPerItem = BigDecimal.valueOf(cart.getCartItemQuantity()).multiply(cartBook.getBook().getPrice());
            amountPrice = amountPrice.add(amountPerItem);
            cart.setAmount(amountPrice);
        }
    }

    private void setCartAmount(Cart cart, CartBook cartBook) {
        cart.setAmount(cart.getAmount().add(BigDecimal.valueOf(cart.getCartItemQuantity()).multiply(cartBook.getBook().getPrice())));
    }

}



