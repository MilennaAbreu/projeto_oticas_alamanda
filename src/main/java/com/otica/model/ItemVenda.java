package com.otica.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ITEMVENDA")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idVenda;
    private Long idProduto;
    private Integer quantidade;
    private Double precoUnitario;
        

    // Getters e Setters
}