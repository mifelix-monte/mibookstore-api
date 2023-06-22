package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.Client;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import com.mibookstore.mibookstoreapi.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class ClientServiceIpmlTest {

    @InjectMocks
    private ClientServiceIpml service;

    @Mock
    private ClientRepository repository;

    Client client;

    ClientRequest clientRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startClient();
    }

    @Test
    void createClient() {
        when(repository.save(any())).thenReturn(client);

        Client response = service.createClient(clientRequest);

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(client.getId(), response.getId());
        assertEquals(client.getName(), response.getName());
        assertEquals(client.getCpf(), response.getCpf());
        assertEquals(client.getEmail(), response.getEmail());
        assertEquals(client.getAddress(), response.getAddress());
        assertEquals(client.getCell(), response.getCell());
    }

    @Test
    void getAll() {
        Page<Client> clients = Mockito.mock(Page.class);
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.ASC,"name");

        when(repository.findAll(pageRequest)).thenReturn(clients);

        Page<Client> response = service.getAll(1,1);

        assertNotNull(response);
    }

    @Test
    void getById() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(client));

        Client response = service.getById(client.getId());

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(client.getId(), response.getId());
    }

    @Test
    void updateClient() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(client));
        when(repository.save(any())).thenReturn(client);

        Client response = service.updateClient(clientRequest, client.getId());

        assertNotNull(response);
        assertEquals(Client.class, response.getClass());
        assertEquals(client.getId(), response.getId());
        assertEquals(client.getName(), response.getName());
        assertEquals(client.getCpf(), response.getCpf());
        assertEquals(client.getEmail(), response.getEmail());
        assertEquals(client.getAddress(), response.getAddress());
        assertEquals(client.getCell(), response.getCell());
    }

    @Test
    void deleteClient() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(client));
        doNothing().when(repository).delete(client);
        service.deleteClient(client.getId());
        verify(repository, times(1)).delete(client);
    }

    private void startClient() {
        client = new Client();
        client.setId(52);
        client.setName("Fabiana da Silva");
        client.setCpf("47884425977");
        client.setEmail("fabiana@hotmail.com");
        client.setAddress("Rua Pico do Baú, 78, Altos da Serra - São José dos Campos/SP");
        client.setCell("11981475521");

        clientRequest = new ClientRequest("Fabiana da Silva", "47884425977", "fabiana@hotmail.com",
                "Rua Pico do Baú, 78, Altos da Serra - São José dos Campos/SP", "11981475521");

    }
}