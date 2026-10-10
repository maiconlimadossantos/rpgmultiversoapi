package com.example.rpgmultiverso.Controller;

import com.example.rpgmultiverso.Model.Item;
import com.example.rpgmultiverso.Repository.ItemRepository;
import com.example.rpgmultiverso.Repository.MochilaRepository;

import jakarta.validation.Valid;

// import removed as it is not needed
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;
    private final MochilaRepository mochilaRepository;

    // Injeção de dependência via construtor
    public ItemController(ItemRepository itemRepository, MochilaRepository mochilaRepository) {
        this.itemRepository = itemRepository;
        this.mochilaRepository = mochilaRepository;
    }

    // Listar todos os itens
    @GetMapping
    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Cadastrar novo item
    @PostMapping
    public ResponseEntity<Item> criar(@RequestBody @Valid Item item) {
        Item novoItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    // Atualizar item existente
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizar(@PathVariable Long id, @RequestBody @Valid Item itemAtualizado) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemAtualizado.setId(id);
        Item itemSalvo = itemRepository.save(itemAtualizado);
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

     @PutMapping("/{itemId}/mochila/{mochilaId}")
    public ResponseEntity<Item> adicionarMochila(
            @PathVariable Long itemId,
            @PathVariable Long mochilaId) {

        
        var item = itemRepository.findById(itemId);
        var mochila = mochilaRepository.findById(mochilaId);

        if (item.isEmpty() || mochila.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        item.get().setMochila(mochila.get());

        Item atualizado = itemRepository.save(item.get());

        return ResponseEntity.ok(atualizado);
    }
}