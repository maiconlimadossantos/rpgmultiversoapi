package com.example.rpgmultiverso.Controller;

import com.example.rpgmultiverso.Model.Mochila;

import com.example.rpgmultiverso.Repository.MochilaRepository;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController 
@RequestMapping("/mochila") 
public class MochilaController {

    private final MochilaRepository mochilaRepository;


    // Injeção de dependência via construtor (A anotação @Autowired é opcional aqui)
    public MochilaController(MochilaRepository mochilaRepository) {
        this.mochilaRepository = mochilaRepository;
       
    }
   

    @GetMapping
    public List<Mochila> listarTodos() {
        return mochilaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mochila> buscarPorId(@PathVariable Long id) { // [CORREÇÃO] Adicionado @PathVariable para capturar o ID da URL
        return mochilaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Mochila> cadastrar(
            @RequestBody Mochila mochila) {

        mochila.setId(null);

        Mochila salva = mochilaRepository.save(mochila);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salva);
    }

  
    
}