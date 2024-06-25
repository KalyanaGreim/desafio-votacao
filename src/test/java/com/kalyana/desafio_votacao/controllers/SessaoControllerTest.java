package com.kalyana.desafio_votacao.controllers;

import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import com.kalyana.desafio_votacao.repositories.ISessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IPautaRepository pautaRepository;

    @Autowired
    private ISessaoRepository sessaoRepository;

    @BeforeEach
    public void setup() {
        sessaoRepository.deleteAll();
        pautaRepository.deleteAll();

        Pauta pauta = new Pauta();
        pauta.setDescricao("Pauta 1");
        pautaRepository.save(pauta);
    }

    @Test
    public void testIniciarSessao() throws Exception {
        mockMvc.perform(post("/sessoes/1/iniciar-sessao")
                        .param("dataFim", LocalDateTime.now().plusMinutes(5).toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testVerificarStatusSessao() throws Exception {
        // Assume que já existe uma sessão aberta para a pauta com id 1
        mockMvc.perform(get("/sessoes/1/status"))
                .andExpect(status().isOk());
    }
}
