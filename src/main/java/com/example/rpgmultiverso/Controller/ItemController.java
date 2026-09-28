package com.example.rpgmultiverso.Controller;

import com.example.rpgmultiverso.Model.Itens;
import com.example.rpgmultiverso.Repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemRepository itemRepository;

    // Injeção de dependência via construtor
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Listar todos os itens
    @GetMapping
    public List<Itens> listarTodos() {
        return itemRepository.findAll();
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public ResponseEntity<Itens> buscarPorId(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Cadastrar novo item
    @PostMapping
    public ResponseEntity<Itens> criar(@RequestBody @Valid Itens item) {
        Itens novoItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    // Atualizar item existente
    @PutMapping("/{id}")
    public ResponseEntity<Itens> atualizar(@PathVariable Long id, @RequestBody @Valid Itens itemAtualizado) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemAtualizado.setId(id);
        Itens itemSalvo = itemRepository.save(itemAtualizado);
        return ResponseEntity.ok(itemSalvo);
    }

    // Deletar item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}