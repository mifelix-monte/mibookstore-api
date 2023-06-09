package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/client")
@Api(value = "Mi Bookstore API")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    @ApiOperation(value = "Create an instance of client")
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRequest clientRequest){

        return ResponseEntity.status(CREATED).body(clientService.createClient(clientRequest));
    }

    @GetMapping
    @ApiOperation(value = "Return a list of all registered clients")
    public ResponseEntity<Page<Client>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                               @RequestParam  (required = false, defaultValue = "3") int size){

        return ResponseEntity.ok(clientService.getAll(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return a client according to the id")
    public ResponseEntity<Client> getById(@PathVariable Integer id){

        return ResponseEntity.ok(clientService.getById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update client information")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody @Valid ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.updateClient(clientRequest, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete client")
    public ResponseEntity deleteClient(@PathVariable Integer id){
        clientService.deleteClient(id);

        return ResponseEntity.noContent().build();
    }
}
