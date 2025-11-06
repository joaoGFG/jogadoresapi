package com.example.generenciadorjogadores.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.generenciadorjogadores.dto.JogadorResponse;
import com.example.generenciadorjogadores.model.Jogador;
import com.example.generenciadorjogadores.model.JogadorFilters;
import com.example.generenciadorjogadores.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    
    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService){
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public PagedModel<EntityModel<JogadorResponse>> getAll(JogadorFilters filters, @PageableDefault(size = 10, sort = "nome") Pageable pageable, PagedResourcesAssembler<Jogador> assembler
    ) {
        var page = jogadorService.listarJogadores(pageable, filters);
        return assembler.toModel(page, JogadorResponse::toEntityModel);
    }    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorResponse criarJogador(@Valid @RequestBody Jogador jogador) {
        var novo = jogadorService.criarJogador(jogador);
        return JogadorResponse.fromModel(novo);
    }

    @GetMapping("/{id}")
    public EntityModel<JogadorResponse> buscarJogadorPorId(@PathVariable Long id) {
        var jogador = jogadorService.findJogadorById(id);
        return JogadorResponse.toEntityModel(jogador);
    }

    @DeleteMapping("/{id}")
    public void deletarJogador(@PathVariable Long id) {
        jogadorService.deletarJogador(id);
    }

    @PatchMapping("/{id}/idade")
    public JogadorResponse atualizarIdade(@PathVariable Long id, @RequestParam Integer idade) {
        var jogador = jogadorService.atualizarIdadeJogador(id, idade);
        return JogadorResponse.fromModel(jogador);
    }
}
