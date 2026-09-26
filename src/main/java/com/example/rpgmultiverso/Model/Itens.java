package com.example.rpgmultiverso.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "itens")
@Data

public class Itens {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double peso;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "mochila_id")
    @JsonIgnore 
    private Mochila mochila;
}
