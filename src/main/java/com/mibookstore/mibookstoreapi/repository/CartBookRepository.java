package com.mibookstore.mibookstoreapi.repository;


import com.mibookstore.mibookstoreapi.model.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Integer> {
    CartBook findByCartIdAndBookId(Integer cartId, Integer bookId);

}
