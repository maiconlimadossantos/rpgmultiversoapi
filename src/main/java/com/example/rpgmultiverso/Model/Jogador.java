package com.example.rpgmultiverso.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="jogador")
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve possuir entre 3 e 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nome;

    @NotBlank(message = "O apelido é obrigatório")
    @Size(min = 3, max = 30, message = "O apelido deve possuir entre 3 e 30 caracteres")
    @Column(nullable = false, length = 30)
    private String apelido;

    @NotBlank(message="A geração do jogador")
    @Column (nullable=false , length=30)
    private int geracao;

    @Min(value = 1, message = "O nível mínimo é 1")
    @Column(nullable = false)
    private Integer nivel = 1; 
}
