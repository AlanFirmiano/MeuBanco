package com.example.meubanco.service;

import com.example.meubanco.entity.Deposito;
import com.example.meubanco.entity.Usuario;
import com.example.meubanco.exception.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositoService {

    UsuarioService usuarioService;

    @Autowired
    public DepositoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario deposito(Deposito deposito) throws UsuarioNotFoundException {
        Usuario usuario = usuarioService.findByConta(deposito.getConta());

        if (usuario != null) {
            usuario.setSaldo(usuario.getSaldo() + deposito.getValor());
            return usuarioService.save(usuario);
        }
        throw new UsuarioNotFoundException("Usuario não existe");
    }
}
