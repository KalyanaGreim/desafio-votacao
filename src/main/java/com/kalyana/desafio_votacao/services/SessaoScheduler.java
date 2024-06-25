package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.entities.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoScheduler {

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private PautaService pautaService;

    @Scheduled(fixedDelay = 60000)
    public void verificarSessoesEncerradas() {
        List<Pauta> pautas = pautaService.listarPautas();

        for (Pauta pauta : pautas) {
            if (pauta.getSessao() != null && sessaoService.isSessaoEncerrada(pauta.getSessao().getId())) {
                pauta.setVotacaoAberta(false);
                pautaService.atualizarPauta(pauta);
            }
        }
    }
}
