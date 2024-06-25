package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PautaServiceTest {

    @Mock
    private IPautaRepository pautaRepository;

    @InjectMocks
    private PautaService pautaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPauta() {
        Pauta pauta = new Pauta();
        pauta.setNome("Nova Pauta");
        pauta.setDescricao("Descrição da Nova Pauta");

        Pauta pautaCriada = new Pauta();
        pautaCriada.setId(1L);
        pautaCriada.setNome(pauta.getNome());
        pautaCriada.setDescricao(pauta.getDescricao());

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaCriada);

        Pauta pautaRetornada = pautaService.criarPauta(pauta);

        assertNotNull(pautaRetornada);
        assertEquals(1L, pautaRetornada.getId());
        assertEquals("Nova Pauta", pautaRetornada.getNome());
        assertEquals("Descrição da Nova Pauta", pautaRetornada.getDescricao());
    }

    @Test
    public void testListarPautas() {
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        pauta1.setNome("Pauta 1");
        pauta1.setDescricao("Descrição da Pauta 1");

        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setNome("Pauta 2");
        pauta2.setDescricao("Descrição da Pauta 2");

        List<Pauta> pautas = Arrays.asList(pauta1, pauta2);

        when(pautaRepository.findAll()).thenReturn(pautas);

        List<Pauta> pautasRetornadas = pautaService.listarPautas();

        assertNotNull(pautasRetornadas);
        assertEquals(2, pautasRetornadas.size());
        assertEquals("Pauta 1", pautasRetornadas.get(0).getNome());
        assertEquals("Descrição da Pauta 2", pautasRetornadas.get(1).getDescricao());
    }

    @Test
    public void testGetPautaById() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        pauta.setNome("Pauta de Teste");
        pauta.setDescricao("Descrição da Pauta de Teste");

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));

        Pauta pautaRetornada = pautaService.getPautaById(1L);

        assertNotNull(pautaRetornada);
        assertEquals(1L, pautaRetornada.getId());
        assertEquals("Pauta de Teste", pautaRetornada.getNome());
        assertEquals("Descrição da Pauta de Teste", pautaRetornada.getDescricao());
    }

}
