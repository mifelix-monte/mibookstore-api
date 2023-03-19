package com.mibookstore.mibookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal amount;

    @Column(name = "item_quantity")
    private Integer cartItemQuantity;

    @Column(name = "creation_date")
    @CreatedDate
    @UpdateTimestamp
    private LocalDateTime creationDate;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartBook> books = new ArrayList<>();

    public Cart() {}
    public Cart(CartRequest cartRequest){
        this.cartItemQuantity = cartRequest.getCartItemQuantity();
    }

    public void addCart(Book book) {
        CartBook cartBook = new CartBook(this, book);
        books.add(cartBook);
        book.getCarts().add(cartBook);
    }

    public void removeTag(Book book) {
        for (Iterator<CartBook> iterator = books.iterator();
             iterator.hasNext(); ) {
            CartBook cartBook = iterator.next();

            if (cartBook.getBook().equals(this) &&
                    cartBook.getCart().equals(books)) {
                iterator.remove();
                cartBook.getCart().getBooks().remove(cartBook);
                cartBook.setBook(null);
                cartBook.setCart(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartItemQuantity, cart.cartItemQuantity) &&
                Objects.equals(creationDate, cart.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemQuantity, creationDate);
    }
}
