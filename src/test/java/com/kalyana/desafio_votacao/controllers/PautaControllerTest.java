package com.kalyana.desafio_votacao.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalyana.desafio_votacao.dtos.PautaDTO;
import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.services.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PautaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private PautaController pautaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pautaController).build();
    }

    @Test
    public void testCriarPauta() throws Exception {
        PautaDTO pautaDTO = new PautaDTO(null, "Nova Pauta", "Descrição da Nova Pauta", false);
        Pauta pautaCriada = new Pauta();
        pautaCriada.setId(1L);
        pautaCriada.setNome(pautaDTO.getNome());
        pautaCriada.setDescricao(pautaDTO.getDescricao());

        when(pautaService.criarPauta(any(Pauta.class))).thenReturn(pautaCriada);

        mockMvc.perform(post("/pautas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pautaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Nova Pauta"))
                .andExpect(jsonPath("$.descricao").value("Descrição da Nova Pauta"))
                .andExpect(jsonPath("$.votacaoAberta").value(false));
    }

    @Test
    public void testGetPautaById() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setNome("Pauta de Teste");
        pauta.setDescricao("Descrição da Pauta de Teste");

        when(pautaService.getPautaById(1L)).thenReturn(pauta);

        mockMvc.perform(get("/pautas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Pauta de Teste"))
                .andExpect(jsonPath("$.descricao").value("Descrição da Pauta de Teste"));
    }

    @Test
    public void testListarPautas() throws Exception {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        pauta1.setNome("Pauta 1");
        pauta1.setDescricao("Descrição da Pauta 1");

        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setNome("Pauta 2");
        pauta2.setDescricao("Descrição da Pauta 2");

        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);

        when(pautaService.listarPautas()).thenReturn(pautas);

        mockMvc.perform(get("/pautas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Pauta 1"))
                .andExpect(jsonPath("$[0].descricao").value("Descrição da Pauta 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Pauta 2"))
                .andExpect(jsonPath("$[1].descricao").value("Descrição da Pauta 2"));
    }
}
