package com.example.generenciadorjogadores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "{jogador.nome.notblank}")
    @Size(min = 2, max = 100, message = "{jogador.nome.size}")
    private String nome;    @NotBlank(message = "{jogador.posicao.notblank}")

    @Size(min = 2, max = 50, message = "{jogador.posicao.size}")
    private String posicao;

    @NotNull(message = "{jogador.idade.notNull}")
    @Min(value = 16, message = "{jogador.idade.min}")
    @Max(value = 45, message = "{jogador.idade.max}")
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "time_id")
    @NotNull(message = "{jogador.time.notNull}")
    private Time time;

    public EntityModel<Jogador> toEntityModel() {
        return EntityModel.of(this)
                .add(linkTo(methodOn(com.example.generenciadorjogadores.controller.JogadorController.class)
                        .buscarJogadorPorId(this.id)).withSelfRel());
    }
}
