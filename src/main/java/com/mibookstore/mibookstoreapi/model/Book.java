package com.mibookstore.mibookstoreapi.model;

import com.fasterxml.jackson.annotation.*;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

//FAZENDO O MAPEAMENTO DE UMA ENTIDADE

@Entity
@Getter
@Setter
@Table(name = "books")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer pages;
    @Column(name = "quantity")
    private Integer stockQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartBook> carts = new ArrayList<>();

    public Book(){}

    public Book(BookRequest bookRequest) {
        this.title = bookRequest.getTitle();
        this.author = bookRequest.getAuthor();
        this.price = bookRequest.getPrice();
        this.pages = bookRequest.getPages();
        this.stockQuantity = bookRequest.getStockQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return id != null && id.equals(((Book) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
