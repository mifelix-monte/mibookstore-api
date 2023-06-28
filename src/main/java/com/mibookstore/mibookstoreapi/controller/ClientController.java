package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRequest clientRequest){

        return ResponseEntity.status(CREATED).body(clientService.createClient(clientRequest));
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                               @RequestParam  (required = false, defaultValue = "3") int size){

        return ResponseEntity.ok(clientService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Integer id){

        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody @Valid ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.updateClient(clientRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Integer id){
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
