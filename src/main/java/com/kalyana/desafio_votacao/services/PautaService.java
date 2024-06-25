package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.dtos.PautaDTO;
import com.kalyana.desafio_votacao.entities.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;

import java.util.List;

@Service
public class PautaService {
    @Autowired
    private IPautaRepository pautaRepository;

    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    public PautaDTO convertToDTO(Pauta pauta) {
        boolean votacaoAberta = pauta.isVotacaoAberta();
        return new PautaDTO(pauta.getId(), pauta.getNome(), pauta.getDescricao(), votacaoAberta);
    }


    public Pauta getPautaById(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
    }

    public Pauta atualizarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }


}
