package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CONDICAOPAGAMENTO")
public class CondicaoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Integer numeroParcelas;
    private Integer intervaloDias;
    

    // Getters e Setters
}