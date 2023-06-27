package com.mibookstore.mibookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "books")
@NoArgsConstructor
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
    private List<CartBook> cartBooks = new ArrayList<>();
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
