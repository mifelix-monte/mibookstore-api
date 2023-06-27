package com.mibookstore.mibookstoreapi.service.impl;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.repository.ClientRepository;
import com.mibookstore.mibookstoreapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(ClientRequest clientRequest){
        Client client = new Client(clientRequest);
        return clientRepository.save(client);
    }
    @Override
    public Page<Client> getAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

        return clientRepository.findAll(pageRequest);
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public Client updateClient(ClientRequest clientRequest, Integer id) {
        Client client = clientRepository.findById(id).orElseThrow();

        client.setName(clientRequest.getName());
        client.setCpf(clientRequest.getCpf());
        client.setEmail(clientRequest.getEmail());
        client.setAddress(clientRequest.getAddress());
        client.setPhone(clientRequest.getPhone());

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id).orElseThrow();

        clientRepository.delete(client);

    }
}
