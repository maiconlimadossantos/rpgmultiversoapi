package com.example.rpgmultiverso.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rpgmultiverso.Model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    
   
}