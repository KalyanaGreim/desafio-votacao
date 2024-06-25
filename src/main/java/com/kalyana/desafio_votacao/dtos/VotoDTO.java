package com.kalyana.desafio_votacao.dtos;

import lombok.Data;

@Data
public class VotoDTO {
    private String cpfAssociado;
    private Long pautaId;
    private Boolean voto;
    private String nome;
}

