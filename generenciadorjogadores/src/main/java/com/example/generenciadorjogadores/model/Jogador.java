package com.example.generenciadorjogadores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String posicao;

    private int idade;    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;

    public EntityModel<Jogador> toEntityModel() {
        return EntityModel.of(this)
                .add(linkTo(methodOn(com.example.generenciadorjogadores.controller.JogadorController.class)
                        .buscarJogadorPorId(this.id)).withSelfRel());
    }
}
