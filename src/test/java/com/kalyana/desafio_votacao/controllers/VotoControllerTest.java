package com.kalyana.desafio_votacao.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalyana.desafio_votacao.dtos.VotoDTO;
import com.kalyana.desafio_votacao.services.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class VotoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VotoService votoService;

    @InjectMocks
    private VotoController votoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(votoController).build();
    }

    @Test
    public void testRegistrarVoto() throws Exception {
        VotoDTO votoDTO = new VotoDTO(1L, "12345678900", "Associado Teste", true);

        when(votoService.registrarVoto(any(VotoDTO.class))).thenReturn(true);

        mockMvc.perform(post("/votos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(votoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Voto registrado com sucesso"));
    }

    @Test
    public void testRegistrarVotoAssociadoJaVotou() throws Exception {
        VotoDTO votoDTO = new VotoDTO(1L, "12345678900", "Associado Teste", true);

        when(votoService.registrarVoto(any(VotoDTO.class))).thenThrow(new RuntimeException("Associado já votou nesta pauta"));

        mockMvc.perform(post("/votos/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(votoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Associado já votou nesta pauta"));
    }

    @Test
    public void testContarVotos() throws Exception {
        Long pautaId = 1L;
        Map<String, Long> resultadoEsperado = new HashMap<>();
        resultadoEsperado.put("Sim", 10L);
        resultadoEsperado.put("Não", 5L);

        when(votoService.contarVotos(pautaId)).thenReturn(resultadoEsperado);

        mockMvc.perform(get("/votos/contar/{pautaId}", pautaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Sim").value(10))
                .andExpect(jsonPath("$.Não").value(5));
    }

}
