package com.example.meubanco.service;

import com.example.meubanco.entity.Usuario;
import com.example.meubanco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario findByConta(String conta) {
        return repository.findByConta(conta);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario findById(Integer id) {
        return repository.findById(id).get();
    }
}
