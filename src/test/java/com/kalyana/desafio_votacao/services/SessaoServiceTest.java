package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.entities.Sessao;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import com.kalyana.desafio_votacao.repositories.ISessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SessaoServiceTest {

    @Mock
    private ISessaoRepository sessaoRepository;

    @Mock
    private IPautaRepository pautaRepository;

    @InjectMocks
    private SessaoService sessaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAbrirSessao() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(Sessao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Sessao sessao = sessaoService.abrirSessao(1L, LocalDateTime.now().plusMinutes(5), null);

        assertNotNull(sessao);
        assertEquals(pauta, sessao.getPauta());
    }

    @Test
    public void testIsSessaoEncerrada() {
        Sessao sessao = new Sessao();
        sessao.setId(1L);
        sessao.setDataFim(LocalDateTime.now().minusMinutes(1));

        when(sessaoRepository.findById(1L)).thenReturn(Optional.of(sessao));

        boolean encerrada = sessaoService.isSessaoEncerrada(1L);

        assertTrue(encerrada);
    }
}
