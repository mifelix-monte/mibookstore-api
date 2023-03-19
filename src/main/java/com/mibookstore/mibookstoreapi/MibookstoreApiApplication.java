package com.mibookstore.mibookstoreapi;

import com.mibookstore.mibookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;

@SpringBootApplication
public class MibookstoreApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(MibookstoreApiApplication.class, args);
	}

}
