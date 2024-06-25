package com.kalyana.desafio_votacao.repositories;

import com.kalyana.desafio_votacao.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPautaRepository extends JpaRepository<Pauta, Long> {}
