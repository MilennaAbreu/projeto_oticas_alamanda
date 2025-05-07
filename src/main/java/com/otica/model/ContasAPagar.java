package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CONTASAPAGAR")
public class ContasAPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idFornecedor;
    private LocalDate dataVencimento;
    private Double valor;
    private Boolean pago;
    

    // Getters e Setters
}