package com.mibookstore.mibookstoreapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientRequest {

    private String name;
    private String cpf;
    private String email;
    private String address;
    private String phone;
}
