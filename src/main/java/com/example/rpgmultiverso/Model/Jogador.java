package com.example.rpgmultiverso.Model;

import jakarta.persistence.CascadeType; // [CORREÇÃO] Importação do CascadeType que estava faltando
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; 
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "jogador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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

    @NotNull(message = "A geração do jogador é obrigatória")
    @Min(value = 0, message = "A geração não pode ser negativa")
    @Column(nullable = false)
    private Integer geracao; 

    @NotNull(message = "O nível é obrigatório")
    @Min(value = 1, message = "O nível mínimo é 1")
    @Column(nullable = false)
    private Integer nivel = 1; 

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mochila_id", referencedColumnName = "id")
    private Mochila mochila;

    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "habilidade_id", referencedColumnName = "id")
    private Habilidade habilidade;

    public void gerarHabilidadePorGeracao() {
        if (this.geracao == null) return;

        Habilidade novaHabilidade = new Habilidade();
        
        switch (this.geracao) {
            case 1:
                novaHabilidade.setNome("Instinto de Sobrevivência");
                novaHabilidade.setDescricao("Habilidade básica voltada para resistir aos perigos primitivos.");
                novaHabilidade.setTipo("Sobrevivência");
                break;
            case 2:
                novaHabilidade.setNome("Domínio de Classe");
                novaHabilidade.setDescricao("Habilidade especializada focada nas características da sua classe escolhida.");
                novaHabilidade.setTipo("Classe");
                break;
            case 3:
                novaHabilidade.setNome("Herança de Clã");
                novaHabilidade.setDescricao("Poder ancestral herdado diretamente das tradições do seu clã.");
                novaHabilidade.setTipo("Clã");
                break;
            default:
                novaHabilidade.setNome("Habilidade Genérica");
                novaHabilidade.setDescricao("Uma habilidade adaptada para gerações avançadas.");
                novaHabilidade.setTipo("Especial");
                break;
        }
        
        this.habilidade = novaHabilidade;
    }

}