package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;
    private String descricao;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private String unidade;
        

    // Getters e Setters
}