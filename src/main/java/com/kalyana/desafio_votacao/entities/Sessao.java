package com.kalyana.desafio_votacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    @JsonIgnore
    private Pauta pauta;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    @Override
    public int hashCode() {
        return dataInicio != null ? dataInicio.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Sessao sessao = (Sessao) obj;

        return dataInicio != null ? dataInicio.equals(sessao.dataInicio) : sessao.dataInicio == null;
    }
}

