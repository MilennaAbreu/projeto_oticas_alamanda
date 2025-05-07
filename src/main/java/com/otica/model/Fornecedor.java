package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FORNECEDOR")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    

    // Getters e Setters
}