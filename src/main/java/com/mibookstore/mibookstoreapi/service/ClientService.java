package com.mibookstore.mibookstoreapi.service;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import org.springframework.data.domain.Page;

public interface ClientService {

    Client createClient(ClientRequest clientRequest);
    Page<Client> getAll(int page, int size);
    Client getById(Integer id);
    Client updateClient(ClientRequest clientRequest, Integer id);
    void deleteClient(Integer id);

}
