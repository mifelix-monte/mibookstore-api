package com.mibookstore.mibookstoreapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartRequest {

    private Integer cartItemQuantity;

}
