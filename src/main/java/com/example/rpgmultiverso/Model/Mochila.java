package com.example.rpgmultiverso.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import java.util.ArrayList;

@Entity 
@Table (name = "mochila")
@Data 


public class Mochila {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private long id;
    
    @NotBlank (message = "")
    @Size (min= ,max=, message="")
    @Column()
    private String nomeDono;

    @NotBlank (message = "")
    @Size (min= ,max=, message="")
    @Column()
    private double capacidadeMaximaPeso;

    @OneToMany (mappedBy = "mochila",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens=new ArrayList<>();

    public  double getPesoAtual(){
        return itens.stream().mapToDouble(Item->itens.getPeso()*item.getQuantidade()).sum();
    }
    

    public boolean adicionarItem(Item item) {
        if(getPesoAtual() +(item.getPeso))
    }


    
}
