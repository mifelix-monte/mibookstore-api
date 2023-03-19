package com.mibookstore.mibookstoreapi.service;

import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.model.dto.CartResponse;

import java.util.List;

public interface CartService {

    Cart addItem(Integer clientId, Integer bookId, CartRequest cartRequest);
    List<Cart> getAll();
    Cart getById(Integer cartId);
//    Cart addItem(Integer cartId, Integer bookId, CartRequest cartRequest);
    Cart removeItem(Integer cartId, Integer bookId);
    void deleteCart(Integer cartId);
}
