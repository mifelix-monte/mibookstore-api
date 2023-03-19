package com.mibookstore.mibookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@Table(name = "clients")
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String cpf;

    private String email;

    private String address;

    private String cell;

    public Client (ClientRequest clientRequest) {
        this.name = clientRequest.getName();
        this.cpf = clientRequest.getCpf();
        this.email = clientRequest.getEmail();
        this.address = clientRequest.getAddress();
        this.cell = clientRequest.getCell();
    }

    public Client () {

    }
}
