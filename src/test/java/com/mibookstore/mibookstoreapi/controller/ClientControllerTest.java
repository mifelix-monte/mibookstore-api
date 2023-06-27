package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.service.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    ClientController clientController;

    private Client client;

    @Mock
    ClientService clientService;

    ClientRequest clientRequest;

    @BeforeEach
    public void setUp() {
        clientRequest = new ClientRequest("Mariana Rios", "21874562588", "marios@hotmail.com", "Rua Pedra Alta, 458, Rosa", "12988541477");
        client = new Client();
    }

    @Test
    public void mustCreatClient() {
        when(clientService.createClient(clientRequest)).thenReturn(client);
        var response = assertDoesNotThrow(() -> clientController.createClient(clientRequest));
        assertEquals(ResponseEntity.status(CREATED).body(clientService.createClient(clientRequest)), response);
        assertNotNull(response);
    }

    @Test
    public void mustGetAllClients() {
        var response = assertDoesNotThrow(() -> clientController.getAll(3, 3));
        assertNotNull(response);
    }

    @Test
    public void mustGetClientById() {
        when(clientService.getById(client.getId())).thenReturn(client);
        var response = assertDoesNotThrow(() -> clientController.getById(client.getId()));
        assertNotNull(response);

    }

    @Test
    public void mustUpdateClient() {
        when(clientService.updateClient(clientRequest, client.getId())).thenReturn(client);
        var response = assertDoesNotThrow(() -> clientController.updateClient(client.getId(), clientRequest));
        assertNotNull(response);
    }

    @Test
    public void mustDeleteClient() {
        var response = assertDoesNotThrow(() -> clientController.deleteClient(client.getId()));
        assertEquals(ResponseEntity.noContent().build(), response);
    }


}
