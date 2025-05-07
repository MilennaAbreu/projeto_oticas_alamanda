package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CONTASARECEBER")
public class ContasAReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;
    private LocalDate dataVencimento;
    private Double valor;
    private Boolean pago;
    

    // Getters e Setters
}