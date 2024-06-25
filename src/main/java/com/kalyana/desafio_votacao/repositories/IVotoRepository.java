package com.kalyana.desafio_votacao.repositories;

import com.kalyana.desafio_votacao.entities.Pauta;
import com.kalyana.desafio_votacao.entities.Associado;
import com.kalyana.desafio_votacao.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByAssociadoAndPauta(Associado associado, Pauta pauta);
    Long countByPautaAndVoto(Pauta pauta, Boolean voto);

    List<Voto> findByAssociadoAndPauta(Associado associado, Pauta pauta);
}
