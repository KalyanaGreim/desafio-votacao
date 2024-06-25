package com.kalyana.desafio_votacao.controllers;

import com.kalyana.desafio_votacao.dtos.VotoDTO;
import com.kalyana.desafio_votacao.services.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/votos")
public class VotoController {
    @Autowired
    private VotoService votoService;

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrarVoto(@RequestBody VotoDTO votoDTO) {
        try {
            boolean votoRegistrado = votoService.registrarVoto(votoDTO);
            if (votoRegistrado) {
                return ResponseEntity.ok(Collections.singletonMap("message", "Voto registrado com sucesso"));
            } else {
                return ResponseEntity.ok(Collections.singletonMap("message", "Erro ao registrar voto"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }


    @GetMapping("/contar/{pautaId}")
    public ResponseEntity<Map<String, Long>> contarVotos(@PathVariable Long pautaId) {
        return ResponseEntity.ok(votoService.contarVotos(pautaId));
    }

}
