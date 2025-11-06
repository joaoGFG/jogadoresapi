package com.example.generenciadorjogadores.filter;

import org.springframework.data.jpa.domain.Specification;
import com.example.generenciadorjogadores.model.Time;
import com.example.generenciadorjogadores.model.TimeFilters;

public class TimeSpecification {

    public static Specification<Time> build(TimeFilters filters) {
        Specification<Time> spec = Specification.anyOf();

        if (filters == null) {
            return spec;
        }

        if (filters.getNome() != null && !filters.getNome().isBlank()) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.upper(root.get("nome")), "%" + filters.getNome().toUpperCase() + "%")
            );
        }

        return spec;
    }
}
