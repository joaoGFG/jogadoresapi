package com.example.generenciadorjogadores.service;

import com.example.generenciadorjogadores.filter.TimeSpecification;
import com.example.generenciadorjogadores.model.Time;
import com.example.generenciadorjogadores.model.TimeFilters;
import com.example.generenciadorjogadores.repository.TimeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }    
    
    public Page<Time> listarTimes(Pageable pageable, TimeFilters filters) {
        Specification<Time> spec = TimeSpecification.build(filters);
        return timeRepository.findAll(spec, pageable);
    }

    public Time criarTime(Time time) {
        return timeRepository.save(time);
    }    
    
    public Time findTimeById(Long id) {
        return timeRepository.findById(id).orElse(null);
    }

    public void deletarTime(Long id) {
        timeRepository.deleteById(id);
    }
}
