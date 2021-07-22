package com.example.meubanco.service;

import com.example.meubanco.entity.Transferencia;
import com.example.meubanco.entity.Usuario;
import com.example.meubanco.exception.SaldoInsuficienteException;
import com.example.meubanco.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    UsuarioService usuarioService;

    @Autowired
    public TransferenciaService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void pix (Transferencia transferencia) throws UsuarioNotFoundException, SaldoInsuficienteException {
        Usuario origem = usuarioService.findByConta(transferencia.getContaOrigem());
        Usuario destino = usuarioService.findByConta(transferencia.getContaDestino());

        if (origem != null && destino != null) {
            if (origem.getSaldo() - transferencia.getValor() >= 0.0) {
                origem.setSaldo(origem.getSaldo()-transferencia.getValor());
                destino.setSaldo(destino.getSaldo()+transferencia.getValor());
                usuarioService.save(origem);
                usuarioService.save(destino);
            } else {
                throw new SaldoInsuficienteException("Saldo Insuficiente");
            }
        } else {
            throw new UsuarioNotFoundException("Usuario origem/destino não existe!");
        }
    }
}
