package com.kalyana.desafio_votacao.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String nome;

    @ManyToMany(mappedBy = "associadosVotaram")
    private Set<Pauta> pautasVotadas = new HashSet<>();

    @Override
    public int hashCode() {
        return cpf != null ? cpf.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Associado associado = (Associado) obj;

        return cpf != null ? cpf.equals(associado.cpf) : associado.cpf == null;
    }
}

