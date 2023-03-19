package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.service.ClientService;
import com.mibookstore.mibookstoreapi.service.ipml.ClientServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientServiceIpml clientServiceIpml;

    @Autowired
    ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody ClientRequest clientRequest){
        return clientServiceIpml.createClient(clientRequest);
    }

    @GetMapping
    public Page<Client> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                               @RequestParam  (required = false, defaultValue = "3") int size){

        return clientService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        return clientServiceIpml.getById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody ClientRequest clientRequest){
        return clientServiceIpml.updateClient(clientRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id){
        clientServiceIpml.deleteClient(id);
    }
}
