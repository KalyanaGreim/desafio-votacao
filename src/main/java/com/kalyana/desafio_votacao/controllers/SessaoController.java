package com.kalyana.desafio_votacao.controllers;

import com.kalyana.desafio_votacao.services.SessaoService;
import com.kalyana.desafio_votacao.entities.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {
    @Autowired
    private SessaoService sessaoService;

    @PostMapping("/{pautaId}/iniciar-sessao")
    public ResponseEntity<Sessao> iniciarSessao(
            @PathVariable Long pautaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim
    ) {
        Sessao sessao = sessaoService.abrirSessao(pautaId, dataInicio, dataFim);
        return ResponseEntity.ok(sessao);
    }

    @GetMapping("/{sessaoId}/status")
    public ResponseEntity<String> verificarStatusSessao(@PathVariable Long sessaoId) {
        boolean sessaoEncerrada = sessaoService.isSessaoEncerrada(sessaoId);

        if (sessaoEncerrada) {
            return ResponseEntity.ok("A sessão de votação foi encerrada");
        } else {
            return ResponseEntity.ok("A sessão de votação está em andamento");
        }
    }

}
