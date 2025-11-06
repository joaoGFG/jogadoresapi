package com.example.generenciadorjogadores.repository;

import com.example.generenciadorjogadores.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>, JpaSpecificationExecutor<Time> {
}
