package com.mibookstore.mibookstoreapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class CartBookId implements Serializable {

    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "book_id")
    private Integer bookId;

    private CartBookId() {}

    public CartBookId(Integer cartId, Integer bookId) {
        this.cartId = cartId;
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartBookId that = (CartBookId) o;
        return Objects.equals(cartId, that.cartId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, bookId);
    }
}
