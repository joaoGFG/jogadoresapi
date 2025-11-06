package com.example.generenciadorjogadores.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;    
    
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jogador> jogadores = new ArrayList<>();

    public EntityModel<Time> toEntityModel() {
        return EntityModel.of(this)
                .add(linkTo(methodOn(com.example.generenciadorjogadores.controller.TimeController.class)
                        .buscarTimePorId(this.id)).withSelfRel());
    }
}
