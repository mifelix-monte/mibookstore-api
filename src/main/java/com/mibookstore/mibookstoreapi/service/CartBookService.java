package com.mibookstore.mibookstoreapi.service;

import com.mibookstore.mibookstoreapi.model.CartBook;

public interface CartBookService {

    CartBook getBookQuantity(Integer cartId, Integer bookId);
}
