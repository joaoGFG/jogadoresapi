package com.example.generenciadorjogadores.model;

import lombok.Data;

@Data
public class JogadorFilters {
    private String nome;
    private String posicao;
    private Integer idade;
    private Integer idadeMinima;
    private Integer idadeMaxima;
    private Long timeId;
}
