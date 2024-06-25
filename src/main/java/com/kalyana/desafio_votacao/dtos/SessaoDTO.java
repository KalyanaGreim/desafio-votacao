package com.kalyana.desafio_votacao.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoDTO {
    private Long pautaId;
    private LocalDateTime dataFim;
}
