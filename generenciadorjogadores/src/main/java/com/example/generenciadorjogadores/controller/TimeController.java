package com.example.generenciadorjogadores.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.generenciadorjogadores.model.Time;
import com.example.generenciadorjogadores.model.TimeFilters;
import com.example.generenciadorjogadores.service.TimeService;

@RestController
@RequestMapping("/times")
public class TimeController {
    
    private final TimeService timeService;

    public TimeController(TimeService timeService){
        this.timeService = timeService;
    }

    @GetMapping
    public PagedModel<EntityModel<Time>> getAll(TimeFilters filters, @PageableDefault(size = 10, sort = "nome") Pageable pageable, PagedResourcesAssembler<Time> assembler) {
        var page = timeService.listarTimes(pageable, filters);
        return assembler.toModel(page, Time::toEntityModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Time criarTime(@RequestBody Time time) {
        return timeService.criarTime(time);
    }

    @GetMapping("/{id}")
    public Time buscarTimePorId(@PathVariable Long id) {
        return timeService.findTimeById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTime(@PathVariable Long id) {
        timeService.deletarTime(id);
    }
}
