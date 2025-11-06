package com.example.generenciadorjogadores.filter;

import org.springframework.data.jpa.domain.Specification;
import com.example.generenciadorjogadores.model.Jogador;
import com.example.generenciadorjogadores.model.JogadorFilters;

public class JogadorSpecification {

    public static Specification<Jogador> build(JogadorFilters filters) {
        Specification<Jogador> spec = Specification.anyOf();

        if (filters == null) {
            return spec;
        }

        if (filters.getNome() != null && !filters.getNome().isBlank()) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.upper(root.get("nome")), "%" + filters.getNome().toUpperCase() + "%")
            );
        }        if (filters.getPosicao() != null && !filters.getPosicao().isBlank()) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.upper(root.get("posicao")), "%" + filters.getPosicao().toUpperCase() + "%")
            );
        }

        if (filters.getIdade() != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("idade"), filters.getIdade())
            );
        }

        if (filters.getIdadeMinima() != null) {
            spec = spec.and((root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("idade"), filters.getIdadeMinima())
            );
        }

        if (filters.getIdadeMaxima() != null) {
            spec = spec.and((root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("idade"), filters.getIdadeMaxima())
            );
        }

        if (filters.getTimeId() != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(root.get("time").get("id"), filters.getTimeId())
            );
        }

        return spec;
    }
}
