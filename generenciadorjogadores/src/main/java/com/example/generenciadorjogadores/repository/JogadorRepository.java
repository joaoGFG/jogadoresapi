package com.example.generenciadorjogadores.repository;

import com.example.generenciadorjogadores.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>, JpaSpecificationExecutor<Jogador> {

}
