package com.kalyana.desafio_votacao.repositories;

import com.kalyana.desafio_votacao.entities.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessaoRepository extends JpaRepository<Sessao, Long> {}
