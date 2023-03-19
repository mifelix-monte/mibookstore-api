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

        Cart cart = cartRepository.findByClientId(client.getId());

        if (cart == null) {
            Cart newCart = new Cart(cartRequest);
            CartBook newCartBook = new CartBook(newCart, book);
            newCart.setClient(client);
            book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
            newCart.getBooks().add(newCartBook);
            getCartAmount(newCart);
            newCartBook.setItemQuantity(cartRequest.getCartItemQuantity());
            return cartRepository.save(newCart);


        } else {
            CartBook cartBook = cartBookRepository.findByCartId(cart.getId());
            if (Objects.equals(bookId, cartBook.getBook().getId())){
                book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
                cartBook.setItemQuantity(cartBook.getItemQuantity() + cartRequest.getCartItemQuantity());
                getCartAmount(cart);
                return cartRepository.save(cart);
            } else {
                CartBook newCartBook = new CartBook(cart, book);
                book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
                newCartBook.setItemQuantity(cartRequest.getCartItemQuantity());
                cart.getBooks().add(newCartBook);
                getCartAmount(cart);
                return cartRepository.save(cart);
            }
        }
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
        cart.getBooks().remove(book);
        getCartAmount(cart);

        book.setStockQuantity(book.getStockQuantity() + cart.getCartItemQuantity());

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
//        updateBookQuantity(cart);
        cartRepository.delete(cart);
    }

    private void getCartAmount(Cart cart) {
        BigDecimal amountPrice = new BigDecimal(0);

        for (CartBook cartBook: cart.getBooks()) {
            amountPrice = amountPrice.add(cartBook.getBook().getPrice());
            cart.setAmount(amountPrice);
        }
    }

//    private void updateBookQuantity(Cart cart){
//
////        for (Book cartBook: cart.getBooks()) {
////            cartBook.setStockQuantity(cartBook.getStockQuantity() + cartBook.getStockQuantity());
////        }
//    }

}



