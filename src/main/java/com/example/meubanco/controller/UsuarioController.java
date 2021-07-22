package com.example.meubanco.controller;

import com.example.meubanco.entity.Usuario;
import com.example.meubanco.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> get() {
        return service.findAll();
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return service.save(usuario);
    }
}
