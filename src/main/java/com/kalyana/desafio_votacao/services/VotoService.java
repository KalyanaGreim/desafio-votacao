package com.kalyana.desafio_votacao.services;

import com.kalyana.desafio_votacao.dtos.VotoDTO;
import com.kalyana.desafio_votacao.entities.Associado;
import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.entities.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kalyana.desafio_votacao.repositories.IAssociadoRepository;
import com.kalyana.desafio_votacao.repositories.IPautaRepository;
import com.kalyana.desafio_votacao.repositories.IVotoRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class VotoService {
    @Autowired
    private IVotoRepository votoRepository;
    @Autowired
    private IAssociadoRepository associadoRepository;
    @Autowired
    private IPautaRepository pautaRepository;


    public boolean registrarVoto(VotoDTO votoDTO) {
        try {
            Pauta pauta = pautaRepository.findById(votoDTO.getPautaId())
                    .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

            Associado associado = associadoRepository.findByCpf(votoDTO.getCpfAssociado())
                    .orElse(null);

            if (associado == null) {
                associado = new Associado();
                associado.setCpf(votoDTO.getCpfAssociado());
                associado.setNome(votoDTO.getNome());
                associado = associadoRepository.save(associado);
            }

            if (votoRepository.existsByAssociadoAndPauta(associado, pauta)) {
                throw new RuntimeException("Associado já votou nesta pauta");
            }

            Voto novoVoto = new Voto();
            novoVoto.setAssociado(associado);
            novoVoto.setPauta(pauta);
            novoVoto.setVoto(votoDTO.getVoto());
            votoRepository.save(novoVoto);

            pauta.getAssociadosVotaram().add(associado);
            pautaRepository.save(pauta);

            return votoDTO.getVoto();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao registrar voto: " + e.getMessage());
        }
    }

    public Map<String, Long> contarVotos(Long pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId).orElseThrow(() -> new RuntimeException("Pauta não encontrada"));
        Long votosSim = votoRepository.countByPautaAndVoto(pauta, true);
        Long votosNao = votoRepository.countByPautaAndVoto(pauta, false);
        Map<String, Long> resultado = new HashMap<>();
        resultado.put("Sim", votosSim);
        resultado.put("Não", votosNao);
        return resultado;
    }
}