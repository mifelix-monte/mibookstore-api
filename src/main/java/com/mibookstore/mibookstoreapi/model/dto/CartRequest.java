package com.mibookstore.mibookstoreapi.model.dto;

import com.mibookstore.mibookstoreapi.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
public class CartRequest {

    private Integer cartItemQuantity;

}
