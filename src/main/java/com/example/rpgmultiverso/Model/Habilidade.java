package com.example.rpgmultiverso.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "habilidade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da habilidade é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotBlank(message = "O tipo da habilidade é obrigatório (Ex: Sobrevivência, Classe, Clã)")
    @Column(nullable = false, length = 30)
    private String tipo; // Ex: "Sobrevivência", "Classe" ou "Clã"

    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}