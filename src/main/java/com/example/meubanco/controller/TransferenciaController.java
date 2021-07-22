package com.example.meubanco.controller;

import com.example.meubanco.entity.Transferencia;
import com.example.meubanco.exception.SaldoInsuficienteException;
import com.example.meubanco.exception.UsuarioNotFoundException;
import com.example.meubanco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class TransferenciaController {

    TransferenciaService service;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.service = transferenciaService;
    }

    @PostMapping
    public void pix(@RequestBody Transferencia transferencia) throws SaldoInsuficienteException, UsuarioNotFoundException {
        service.pix(transferencia);
    }
}
