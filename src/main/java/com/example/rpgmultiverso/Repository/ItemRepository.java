package com.example.rpgmultiverso.Repository;

import com.example.rpgmultiverso.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {

}