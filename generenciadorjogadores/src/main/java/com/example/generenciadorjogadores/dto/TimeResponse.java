package com.example.generenciadorjogadores.dto;

import com.example.generenciadorjogadores.model.Time;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.generenciadorjogadores.controller.TimeController;

import java.util.List;
import java.util.stream.Collectors;

public record TimeResponse(Long id, String nome, List<JogadorResponse> jogadores) {    public static TimeResponse fromModel(Time time) {
        List<JogadorResponse> jogadoresResponse = time.getJogadores() != null 
            ? time.getJogadores().stream()
                .map(JogadorResponse::fromModelWithoutTime)
                .collect(Collectors.toList())
            : List.of();

        return new TimeResponse(
                time.getId(),
                time.getNome(),
                jogadoresResponse
        );
    }

    public static EntityModel<TimeResponse> toEntityModel(Time time) {
        TimeResponse response = fromModel(time);

        return EntityModel.of(
                response,
                linkTo(methodOn(TimeController.class).buscarTimePorId(time.getId())).withSelfRel(),
                linkTo(methodOn(TimeController.class).getAll(null, null, null)).withRel("times")
        );
    }
}
