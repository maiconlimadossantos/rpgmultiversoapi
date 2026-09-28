package com.example.rpgmultiverso.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity 
@Table(name = "mochila")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
public class Mochila {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do dono é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve possuir entre 3 e 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nomeDono;

    @NotNull(message = "A capacidade máxima de peso é obrigatória")
    @Min(value = 0, message = "O peso mínimo não pode ser negativo")
    @Column(nullable = false)
    private Double capacidadeMaximaPeso;

    @OneToMany(mappedBy = "mochila", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Itens> itens = new ArrayList<>();

    // [CORREÇÃO] Substituído "itens.getPeso()" por "item.getPeso()" no parâmetro da lambda
    public double getPesoAtual() {
        return itens.stream()
                    .mapToDouble(item -> item.getPeso() * item.getQuantidade())
                    .sum();
    }

    // [CORREÇÃO] Corrigido "item.getPeso" para "item.getPeso()" e adicionadas as chaves que faltavam no método e no if
    public boolean adicionarItem(Itens item) {
        if (getPesoAtual() + (item.getPeso() * item.getQuantidade()) <= capacidadeMaximaPeso) {
            itens.add(item);
            item.setMochila(this);
            return true;
        }
        return false;
    }
}