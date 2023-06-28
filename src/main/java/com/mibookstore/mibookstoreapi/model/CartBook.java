package com.mibookstore.mibookstoreapi.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartBook {

    @EmbeddedId
    private CartBookId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    public CartBook(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
        this.id = new CartBookId(cart.getId(), book.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartBook that = (CartBook) o;
        return Objects.equals(cart, that.cart) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, book);
    }
}
