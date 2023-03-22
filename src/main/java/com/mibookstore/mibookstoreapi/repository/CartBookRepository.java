package com.mibookstore.mibookstoreapi.repository;


import com.mibookstore.mibookstoreapi.model.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Integer> {


//    List<CartBook> findAllByCartId(Integer cartId);

//    CartBook findBookById(Integer bookId);

    CartBook findByCartIdAndBookId(Integer cartId, Integer bookId);

}
