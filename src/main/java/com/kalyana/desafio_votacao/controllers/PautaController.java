package com.kalyana.desafio_votacao.controllers;

import com.kalyana.desafio_votacao.dtos.PautaDTO;
import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.services.PautaService;
import com.kalyana.desafio_votacao.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setNome(pautaDTO.getNome());
        pauta.setDescricao(pautaDTO.getDescricao());

        Pauta pautaCriada = pautaService.criarPauta(pauta);

        PautaDTO responseDTO = new PautaDTO(pautaCriada.getId(), pautaCriada.getNome(), pautaCriada.getDescricao(), false);

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pauta> getPautaById(@PathVariable Long id) {
        Pauta pauta = pautaService.getPautaById(id);
        return ResponseEntity.ok(pauta);
    }

    @GetMapping
    public ResponseEntity<List<PautaDTO>> listarPautas() {
        List<Pauta> pautas = pautaService.listarPautas();
        List<PautaDTO> responseDTOs = new ArrayList<>();

        for (Pauta pauta : pautas) {
            boolean votacaoAberta = pauta.isVotacaoAberta();
            PautaDTO dto = new PautaDTO(pauta.getId(), pauta.getNome(), pauta.getDescricao(), votacaoAberta);

            if (pauta.getSessao() != null) {
                dto.setSessaoId(pauta.getSessao().getId());
            }

            responseDTOs.add(dto);
        }

        return ResponseEntity.ok(responseDTOs);
    }


}
