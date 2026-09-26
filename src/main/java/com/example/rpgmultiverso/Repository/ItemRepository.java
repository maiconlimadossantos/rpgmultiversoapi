package com.example.rpgmultiverso.Repository;

import com.example.rpgmultiverso.Model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Itens, Long> {

}