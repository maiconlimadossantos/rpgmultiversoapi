package com.example.rpgmultiverso.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpgmultiverso.Repository.JogadorRepository;

@RestController
@RequestMapping("/jagador")
public class JogadorController {
  private final JogadorRepository;

  public JogadorController(JogadorRepository repository){
    this.repository=repository;
  }
}
