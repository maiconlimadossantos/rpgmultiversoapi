package com.example.rpgmultiverso.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens")
@Data
@Setter 
@Getter 
public class Itens {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "mochila_id")
    @JsonIgnore 
    private Mochila mochila;
}