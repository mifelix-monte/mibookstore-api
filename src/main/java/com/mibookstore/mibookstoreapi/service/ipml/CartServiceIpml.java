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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

        Cart cart = new Cart(cartRequest);
        CartBook cartBook = new CartBook(cart, book);
        cart.setClient(client);
        book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
        cart.getCartBooks().add(cartBook);
        setCartAmount(cart, cartBook, cartRequest);
        cartBook.setItemQuantity(cartRequest.getCartItemQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Integer cartId, Integer bookId, CartRequest cartRequest) {
        Cart cart = getById(cartId);
        Book book = bookService.getById(bookId);
        CartBook cartBook = cartBookRepository.findByCartIdAndBookId(cartId, bookId);

        if (!(cartBook == null)) {
            book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
            cartBook.setItemQuantity(cartBook.getItemQuantity() + cartRequest.getCartItemQuantity());
            setCartAmount(cart, cartBook, cartRequest);
        } else {
            CartBook newCartBook = new CartBook(cart, book);
            book.setStockQuantity(book.getStockQuantity() - cartRequest.getCartItemQuantity());
            newCartBook.setItemQuantity(cartRequest.getCartItemQuantity());
            cart.getCartBooks().add(newCartBook);
            setCartAmount(cart, newCartBook, cartRequest);
        }

        cart.setCartItemQuantity(cart.getCartItemQuantity() + cartRequest.getCartItemQuantity());
        return cartRepository.save(cart);
    }

    @Override
    public Page<Cart> getAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return cartRepository.findAll(pageRequest);
    }

    @Override
    public Cart getById(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public Cart removeItem(Integer cartId, Integer bookId) {
        final Integer ONE_ITEM_QUANTITY = 1;
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Book book = bookService.getById(bookId);
        CartBook cartBook = cartBookRepository.findByCartIdAndBookId(cartId, bookId);

        if (cartBook.getItemQuantity() > ONE_ITEM_QUANTITY) {
            cartBook.setItemQuantity(cartBook.getItemQuantity() - ONE_ITEM_QUANTITY);
        } else if (Objects.equals(cartBook.getItemQuantity(), ONE_ITEM_QUANTITY)) {
            cart.getCartBooks().remove(cartBook);
        }

        book.setStockQuantity(book.getStockQuantity() + ONE_ITEM_QUANTITY);
        cart.setCartItemQuantity(cart.getCartItemQuantity() - ONE_ITEM_QUANTITY);
        cart.setAmount(cart.getAmount().subtract(book.getPrice()));

        return cartRepository.save(cart);
    }

    @Override
    public Cart clearCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        final Integer RESET_ITEMS = 0;

        for (CartBook item : cart.getCartBooks()) {
            item.getBook().setStockQuantity(item.getBook().getStockQuantity() + item.getItemQuantity());
        }
        cart.setCartItemQuantity(RESET_ITEMS);
        cart.getCartBooks().clear();

        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();

        for (CartBook item : cart.getCartBooks()) {
            item.getBook().setStockQuantity(item.getBook().getStockQuantity() + item.getItemQuantity());
        }
        cartRepository.delete(cart);
    }

    private void setCartAmount(Cart cart, CartBook cartBook, CartRequest cartRequest) {
        if (cart.getAmount() == null) {
            cart.setAmount(BigDecimal.valueOf(cart.getCartItemQuantity()).multiply(cartBook.getBook().getPrice()));
        } else cart.setAmount(cart.getAmount().add(BigDecimal.valueOf(cartRequest.getCartItemQuantity()).multiply(cartBook.getBook().getPrice())));
    }

}



