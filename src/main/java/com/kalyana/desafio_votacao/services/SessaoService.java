package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.entities.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import com.kalyana.desafio_votacao.repositories.ISessaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class SessaoService {
    @Autowired
    private ISessaoRepository sessaoRepository;

    @Autowired
    private IPautaRepository pautaRepository;

    public Sessao abrirSessao(Long pautaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        if (pauta.getSessao() != null && pauta.getSessao().getDataFim().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Já existe uma sessão aberta para esta pauta");
        }

        Sessao sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataInicio(dataInicio != null ? dataInicio : LocalDateTime.now());
        sessao.setDataFim(dataFim != null ? dataFim : LocalDateTime.now().plusMinutes(1));

        sessao = sessaoRepository.save(sessao);

        pauta.setSessao(sessao);
        pautaRepository.save(pauta);

        return sessao;
    }


    public boolean isSessaoEncerrada(Long sessaoId) {
        Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        return sessao.getDataFim().isBefore(LocalDateTime.now());
    }

}

