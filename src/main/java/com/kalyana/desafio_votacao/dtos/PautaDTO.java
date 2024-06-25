package com.kalyana.desafio_votacao.dtos;

import lombok.Data;

@Data
public class PautaDTO {
    private Long id;
    private String nome;
    private String descricao;

    private boolean votacaoAberta;

    private Long sessaoId;

    public PautaDTO(Long id, String nome, String descricao, boolean votacaoAberta) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.votacaoAberta = votacaoAberta;
    }
}

