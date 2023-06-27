package com.mibookstore.mibookstoreapi.repository;

import com.mibookstore.mibookstoreapi.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Page<Client> findAll(Pageable pageable);
    Optional<Client> findById(Integer id);
}
