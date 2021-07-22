package com.example.meubanco.controller;

import com.example.meubanco.entity.Deposito;
import com.example.meubanco.entity.Usuario;
import com.example.meubanco.exception.UsuarioNotFoundException;
import com.example.meubanco.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposito")
public class DepositoController {

    DepositoService depositoService;

    @Autowired
    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @PostMapping
    public Usuario deposito(@RequestBody Deposito deposito) throws UsuarioNotFoundException {
        return depositoService.deposito(deposito);
    }
}
