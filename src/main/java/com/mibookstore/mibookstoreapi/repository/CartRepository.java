package com.mibookstore.mibookstoreapi.repository;

import com.mibookstore.mibookstoreapi.model.Cart;

import com.mibookstore.mibookstoreapi.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Page<Cart> findAll(Pageable pageable);

}
