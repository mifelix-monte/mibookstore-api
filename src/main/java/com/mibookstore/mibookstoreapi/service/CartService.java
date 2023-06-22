package com.mibookstore.mibookstoreapi.service;

import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {

    Cart addItem(Integer clientId, Integer bookId, CartRequest cartRequest);
    Page<Cart> getAll(int page, int size);
    Cart getById(Integer cartId);
    Cart updateCart(Integer clientId, Integer bookId, CartRequest cartRequest);
    Cart removeItem(Integer cartId, Integer bookId);
    Cart clearCart(Integer cartId);
    void deleteCart(Integer cartId);
}
