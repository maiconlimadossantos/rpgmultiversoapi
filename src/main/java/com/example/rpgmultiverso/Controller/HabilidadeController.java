package com.example.rpgmultiverso.Controller;

import com.example.rpgmultiverso.Model.Habilidade;
import com.example.rpgmultiverso.Repository.HabilidadeRepository;
import com.example.rpgmultiverso.Model.Jogador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
@RequestMapping("/habilidade")
public class HabilidadeController {
    private final HabilidadeRepository habilidadeRepository;
    private final JogadorController jogadorController;

    public HabilidadeController(HabilidadeRepository habilidadeRepository, JogadorController jogadorController) {
        this.habilidadeRepository = habilidadeRepository;
        this.jogadorController = jogadorController;
    }

    @GetMapping
    public List<Habilidade> listarTodos() {
        return habilidadeRepository.findAll();
    }

    @GetMapping( "/{id}")
    public ResponseEntity<Habilidade> buscarPorId(@PathVariable Long id) {
        return habilidadeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Habilidade> criar(@RequestBody Habilidade habilidade, @RequestParam Long jogadorId) {
        ResponseEntity<Jogador> jogadorResponse = jogadorController.buscarPorId(jogadorId);
        if (!jogadorResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Jogador jogador = jogadorResponse.getBody();
        habilidade.setJogador(jogador);
        Habilidade novaHabilidade = habilidadeRepository.save(habilidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaHabilidade);
    }

    @PutMapping("path/{id}")
    public ResponseEntity<Habilidade> atualizar(@PathVariable Long id, @RequestBody Habilidade habilidadeAtualizada) {
        if (!habilidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        habilidadeAtualizada.setId(id);
        Habilidade habilidadeSalva = habilidadeRepository.save(habilidadeAtualizada);
        return ResponseEntity.ok(habilidadeSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!habilidadeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        habilidadeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{habilidadeId}/jogador/{jogadorId}")
    public ResponseEntity<Habilidade> associarHabilidadeAoJogador(
            @PathVariable Long habilidadeId,
            @PathVariable Long jogadorId) {

        var habilidadeOpt = habilidadeRepository.findById(habilidadeId);
        var jogadorResponse = jogadorController.buscarPorId(jogadorId);

        if (habilidadeOpt.isEmpty() || !jogadorResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.notFound().build();
        }

        Habilidade habilidade = habilidadeOpt.get();
        Jogador jogador = jogadorResponse.getBody();

        habilidade.setJogador(jogador);
        Habilidade atualizado = habilidadeRepository.save(habilidade);

        return ResponseEntity.ok(atualizado);
    }
    
}
