package com.mibookstore.mibookstoreapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {

    private String name;
    private String cpf;
    private String email;
    private String address;
    private String cell;
}
