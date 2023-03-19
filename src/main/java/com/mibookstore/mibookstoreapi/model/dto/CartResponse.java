package com.mibookstore.mibookstoreapi.model.dto;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.CartBook;
import com.mibookstore.mibookstoreapi.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CartResponse{

    private Integer id;
    private BigDecimal amount;
    private Integer itemQuantity;
    private LocalDateTime creationDate;
    private Client client;
    private List<CartBook> books;

    public CartResponse (Cart cart){
        this.id = cart.getId();
        this.amount = cart.getAmount();
        this.itemQuantity = cart.getCartItemQuantity();
        this.creationDate = cart.getCreationDate();
        this.client = cart.getClient();
        this.books = cart.getBooks();
    }

}
