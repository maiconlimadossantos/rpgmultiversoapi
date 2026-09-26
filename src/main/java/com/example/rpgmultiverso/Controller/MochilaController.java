package com.example.rpgmultiverso.Controller;

import com.example.rpgmultiverso.Model.Itens;
import com.example.rpgmultiverso.Model.Mochila;
import com.example.rpgmultiverso.Repository.ItemRepository;
import com.example.rpgmultiverso.Repository.MochilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service 
public class MochilaController {
    @Autowired 
    private  MochilaRepository mochilaRepository;

    @Autowired 
    private ItemRepository itemRepository;

@GetMapping
public List<Mochila> listartodos(){
    
    return mochilaRepository.findAll();
}

@GetMapping("/{id}")
public Mochila buscarPorId(Long id){
    return mochilaRepository.findById(id).orElse(null);
}

@PostMapping
public Mochila salvar(Mochila mochila){
    return mochilaRepository.save(mochila);
}

}

