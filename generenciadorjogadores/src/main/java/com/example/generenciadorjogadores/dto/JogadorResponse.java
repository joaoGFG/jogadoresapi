package com.example.generenciadorjogadores.dto;

import com.example.generenciadorjogadores.model.Jogador;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.generenciadorjogadores.controller.JogadorController;

public record JogadorResponse(Long id, String nome, int idade, String posicao, String nomeTime) {

    public static JogadorResponse fromModel(Jogador jogador) {
        return new JogadorResponse(
                jogador.getId(),
                jogador.getNome(),
                jogador.getIdade(),
                jogador.getPosicao(),
                jogador.getTime() != null ? jogador.getTime().getNome() : null
        );
    }    
    
    public static JogadorResponse fromModelWithoutTime(Jogador jogador) {
        return new JogadorResponse(
                jogador.getId(),
                jogador.getNome(),
                jogador.getIdade(),
                jogador.getPosicao(),
                jogador.getTime() != null ? jogador.getTime().getNome() : null
        );
    }

    public static EntityModel<JogadorResponse> toEntityModel(Jogador jogador) {
        JogadorResponse response = fromModel(jogador);

        return EntityModel.of(
                response,
                linkTo(methodOn(JogadorController.class).buscarJogadorPorId(jogador.getId())).withSelfRel(),
                linkTo(methodOn(JogadorController.class).getAll(null, null, null)).withRel("jogadores")
        );
    }
}
