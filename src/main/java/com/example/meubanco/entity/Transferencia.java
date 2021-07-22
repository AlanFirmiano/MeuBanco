package com.example.meubanco.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transferencia {
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
}
