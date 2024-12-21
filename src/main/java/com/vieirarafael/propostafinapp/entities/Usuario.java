package com.vieirarafael.propostafinapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private Double renda;
    @OneToOne(mappedBy = "usuario")
    private Proposta proposta;
}
