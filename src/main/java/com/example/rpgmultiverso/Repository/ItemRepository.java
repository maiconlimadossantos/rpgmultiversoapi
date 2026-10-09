package com.example.rpgmultiverso.Repository;

import com.example.rpgmultiverso.Model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Itens, Long> {

}