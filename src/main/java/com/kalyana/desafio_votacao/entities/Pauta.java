package com.kalyana.desafio_votacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    private Boolean votacaoAberta;

    @ManyToMany
    @JoinTable(
            name = "pauta_associado_votou",
            joinColumns = @JoinColumn(name = "pauta_id"),
            inverseJoinColumns = @JoinColumn(name = "associado_id")
    )
    private Set<Associado> associadosVotaram = new HashSet<>();

    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"pauta", "hibernateLazyInitializer", "handler"})
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"pauta", "hibernateLazyInitializer", "handler"})
    private List<Voto> votos = new ArrayList<>();

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pauta pauta = (Pauta) obj;

        return nome != null ? nome.equals(pauta.nome) : pauta.nome == null;
    }

    public boolean isVotacaoAberta() {
        return sessao != null && sessao.getDataFim().isAfter(LocalDateTime.now());
    }

    public void abrirSessao(LocalDateTime dataFim) {
        if (sessao == null) {
            sessao = new Sessao();
            sessao.setPauta(this);
        }
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setDataFim(dataFim != null ? dataFim : LocalDateTime.now().plusMinutes(1));
    }
}
