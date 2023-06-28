package com.mibookstore.mibookstoreapi.model;

import com.mibookstore.mibookstoreapi.model.dto.ClientRequest;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
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
    private String phone;

    public Client (ClientRequest clientRequest) {
        this.name = clientRequest.getName();
        this.cpf = clientRequest.getCpf();
        this.email = clientRequest.getEmail();
        this.address = clientRequest.getAddress();
        this.phone = clientRequest.getPhone();
    }
}

