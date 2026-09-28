package com.example.rpgmultiverso.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpgmultiverso.Model.Jogador;
import com.example.rpgmultiverso.Model.Mochila;
import com.example.rpgmultiverso.Repository.JogadorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogador") 
public class JogadorController {

    private final JogadorRepository repository; 

    public JogadorController(JogadorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Jogador> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        Optional<Jogador> jogador = repository.findById(id);
        return jogador.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jogador> criar(@RequestBody @Valid Jogador jogador) {
        Jogador novoJogador = repository.save(jogador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @RequestBody @Valid Jogador jogadorAtualizado) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jogadorAtualizado.setId(id);
        Jogador jogadorSalvo = repository.save(jogadorAtualizado);
        return ResponseEntity.ok(jogadorSalvo);
    }

    // ENDPOINT: Adicionar ou atualizar a Mochila de um Jogador específico
    @PutMapping("/{id}/mochila")
    public ResponseEntity<Jogador> atualizarMochila(@PathVariable Long id, @RequestBody @Valid Mochila novaMochila) {
        Optional<Jogador> jogadorOpt = repository.findById(id);
        
        if (jogadorOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Jogador jogador = jogadorOpt.get();
        jogador.setMochila(novaMochila); // Associa a nova mochila
        
        Jogador jogadorSalvo = repository.save(jogador);
        return ResponseEntity.ok(jogadorSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}