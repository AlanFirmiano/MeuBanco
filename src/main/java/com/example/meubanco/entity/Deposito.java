package com.example.meubanco.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Deposito {
    private String conta;
    private Double valor;
}
