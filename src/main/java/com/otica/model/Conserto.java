package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CONSERTO")
public class Conserto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    private String descricao;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Double valor;
    

    // Getters e Setters
}