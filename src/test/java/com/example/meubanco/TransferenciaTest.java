package com.example.meubanco;

import com.example.meubanco.entity.Transferencia;
import com.example.meubanco.entity.Usuario;
import com.example.meubanco.exception.SaldoInsuficienteException;
import com.example.meubanco.exception.UsuarioNotFoundException;
import com.example.meubanco.service.TransferenciaService;
import com.example.meubanco.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TransferenciaTest {

    @InjectMocks
    TransferenciaService service;

    @Mock
    UsuarioService usuarioService;

    @Test
    public void deveRealizarPix() throws SaldoInsuficienteException, UsuarioNotFoundException {
        Usuario origem = new Usuario();
        origem.setConta("1234");
        origem.setSaldo(5000.0); // 4500

        Usuario destino = new Usuario();
        destino.setConta("4321");
        destino.setSaldo(400.00); // 900

        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem("1234");
        transferencia.setContaDestino("4321");
        transferencia.setValor(500.00);

        Mockito.when(usuarioService.findByConta("1234")).thenReturn(origem);
        Mockito.when(usuarioService.findByConta("4321")).thenReturn(destino);

        service.pix(transferencia);

        Assertions.assertEquals(4500.00, origem.getSaldo());
        Assertions.assertEquals(900.00, destino.getSaldo());
    }

}
