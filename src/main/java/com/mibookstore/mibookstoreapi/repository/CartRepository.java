package com.mibookstore.mibookstoreapi.repository;

import com.mibookstore.mibookstoreapi.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    Cart findByClientId(Integer id);
}
