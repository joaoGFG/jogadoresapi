package com.example.generenciadorjogadores.service;

import com.example.generenciadorjogadores.model.Jogador;
import com.example.generenciadorjogadores.model.JogadorFilters;
import com.example.generenciadorjogadores.repository.JogadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {    
    
    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }
    
    public Page<Jogador> listarJogadores(Pageable pageable, JogadorFilters filters) {
        return jogadorRepository.findAll(pageable);
    }    
    
    public Jogador criarJogador(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    public Jogador findJogadorById(Long id) {
        return jogadorRepository.findById(id).orElse(null);        
    }

    public void deletarJogador(Long id) {
        jogadorRepository.deleteById(id);
    }

    public Jogador atualizarIdadeJogador(Long id, Integer idade) {
        Jogador jogador = findJogadorById(id);
        jogador.setIdade(idade);
        return jogadorRepository.save(jogador);
    }

}
