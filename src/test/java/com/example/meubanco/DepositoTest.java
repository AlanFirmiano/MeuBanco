package com.example.meubanco;

import com.example.meubanco.entity.Deposito;
import com.example.meubanco.entity.Usuario;
import com.example.meubanco.exception.UsuarioNotFoundException;
import com.example.meubanco.service.DepositoService;
import com.example.meubanco.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DepositoTest {

    @InjectMocks
    private DepositoService depositoService;

    @Mock
    UsuarioService usuarioService;

    @Test
    public void deveRealizarDeposito() throws UsuarioNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setConta("1010");
        usuario.setNome("ticiana");
        usuario.setCpf("12345678");
        usuario.setIdade(25);

        Deposito deposito = new Deposito();
        deposito.setConta("1010");
        deposito.setValor(100.00);

        Mockito.when(usuarioService.findByConta("1010")).thenReturn(usuario);
        depositoService.deposito(deposito);

        Assertions.assertEquals(100.00, usuario.getSaldo());
        Assertions.assertEquals("ticiana", usuario.getNome());
    }

    @Test
    public void deveChamarUsuarioNotFound() {
        Deposito deposito = new Deposito();
        deposito.setConta("1010");
        deposito.setValor(100.00);

        Mockito.when(usuarioService.findByConta("1010")).thenReturn(null);

        UsuarioNotFoundException exception = Assertions.assertThrows(
                UsuarioNotFoundException.class, () -> depositoService.deposito(deposito));

        Assertions.assertEquals("Usuario não existe", exception.getMessage());
    }

}
