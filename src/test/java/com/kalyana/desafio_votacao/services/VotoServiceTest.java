package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.dtos.VotoDTO;
import com.kalyana.desafio_votacao.entities.Associado;
import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.entities.Voto;
import com.kalyana.desafio_votacao.repositories.IAssociadoRepository;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import com.kalyana.desafio_votacao.repositories.IVotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VotoServiceTest {

    @Mock
    private IVotoRepository votoRepository;

    @Mock
    private IAssociadoRepository associadoRepository;

    @Mock
    private IPautaRepository pautaRepository;

    @InjectMocks
    private VotoService votoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarVoto() {
        VotoDTO votoDTO = new VotoDTO(1L, "12345678900", "Associado Teste", true);

        Pauta pauta = new Pauta();
        pauta.setId(1L);

        Associado associado = new Associado();
        associado.setCpf(votoDTO.getCpfAssociado());
        associado.setNome(votoDTO.getNome());

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(associadoRepository.findByCpf("12345678900")).thenReturn(Optional.of(associado));
        when(votoRepository.existsByAssociadoAndPauta(any(Associado.class), any(Pauta.class))).thenReturn(false);

        when(votoRepository.save(any(Voto.class))).thenAnswer(invocation -> {
            Voto votoSalvo = invocation.getArgument(0);
            votoSalvo.setId(1L); // Simulando salvamento com ID
            return votoSalvo;
        });

        boolean votoRegistrado = votoService.registrarVoto(votoDTO);

        assertTrue(votoRegistrado);
    }

    @Test
    public void testContarVotos() {
        Pauta pauta = new Pauta();
        pauta.setId(1L);

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(votoRepository.countByPautaAndVoto(pauta, true)).thenReturn(10L);
        when(votoRepository.countByPautaAndVoto(pauta, false)).thenReturn(5L);

        Map<String, Long> resultado = votoService.contarVotos(1L);

        assertNotNull(resultado);
        assertEquals(10L, resultado.get("Sim"));
        assertEquals(5L, resultado.get("Não"));
    }

}
