package com.mibookstore.mibookstoreapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookRequest {

    private String title;
    private String author;
    private BigDecimal price;
    private Integer pages;
    private Integer stockQuantity;
}
