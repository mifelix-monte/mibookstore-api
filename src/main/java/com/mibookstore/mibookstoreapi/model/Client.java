package com.mibookstore.mibookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@Table(name = "clients")
@ToString
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String cell;

    public Client (ClientRequest clientRequest) {
        this.name = clientRequest.getName();
        this.cpf = clientRequest.getCpf();
        this.email = clientRequest.getEmail();
        this.address = clientRequest.getAddress();
        this.cell = clientRequest.getCell();
    }
}
