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
    @Min(value = 0, message = "A capacidade não pode ser negativa")
    @Column(nullable = false)
    private Double capacidadeMaximaPeso;

    //@OneToMany (mappedBy = "mochila",cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Item> itens=new ArrayList<>();

    //public  double getPesoAtual(){
     //   return itens.stream().mapToDouble(Item->itens.getPeso()*item.getQuantidade()).sum();
   // }
    

    //public boolean adicionarItem(Item item) {
       /// if(getPesoAtual() +(item.getPeso* item.getQuantidade()) <= capacidadeMaximaPeso){
        //    itens.add(item);
         //   item.setMochila(this);
        //    return true;
   // }


    
}
