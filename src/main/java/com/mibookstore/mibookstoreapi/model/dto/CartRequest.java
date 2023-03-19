package com.mibookstore.mibookstoreapi.model.dto;

import com.mibookstore.mibookstoreapi.model.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class CartRequest {

    @Autowired
    Book book;

    private Integer cartItemQuantity;

}
