package com.example.generenciadorjogadores.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.generenciadorjogadores.dto.TimeResponse;
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
    public PagedModel<EntityModel<TimeResponse>> getAll(TimeFilters filters, @PageableDefault(size = 10, sort = "nome") Pageable pageable, PagedResourcesAssembler<Time> assembler) {
        var page = timeService.listarTimes(pageable, filters);
        return assembler.toModel(page, TimeResponse::toEntityModel);
    }    
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeResponse criarTime(@Valid @RequestBody Time time) {
        var timeCreated = timeService.criarTime(time);
        return TimeResponse.fromModel(timeCreated);
    }

    @GetMapping("/{id}")
    public EntityModel<TimeResponse> buscarTimePorId(@PathVariable Long id) {
        var time = timeService.findTimeById(id);
        return TimeResponse.toEntityModel(time);
    }

    @DeleteMapping("/{id}")
    public void deletarTime(@PathVariable Long id) {
        timeService.deletarTime(id);
    }
}
