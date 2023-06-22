package com.mibookstore.mibookstoreapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BookRequest {

    private String title;
    private String author;
    private BigDecimal price;
    private Integer pages;
    private Integer stockQuantity;
}
