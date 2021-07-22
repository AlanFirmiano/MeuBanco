package com.example.meubanco;

import com.example.meubanco.entity.Usuario;
import com.example.meubanco.repository.UsuarioRepository;
import com.example.meubanco.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

//@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioTest {

    @InjectMocks
    UsuarioService usuarioService;

    @Mock
    UsuarioRepository repository;

    @Test
    public void deveCriarUsuarioComSaldoZerado() {
        Usuario user = new Usuario();
        user.setNome("alan");
        user.setConta("1234");
        user.setIdade(22);

        Assertions.assertEquals(0.0, user.getSaldo());
        Assertions.assertEquals("alan", user.getNome());
        Assertions.assertEquals("1234", user.getConta());
        Assertions.assertEquals(22, user.getIdade());
    }

    @Test
    public void deveRetornarUmUsuario() {
        // Arrange
        Usuario user = new Usuario();
        user.setNome("alan");
        user.setConta("1234");
        user.setIdade(22);

        List<Usuario> bancoDados = new ArrayList<>();
        bancoDados.add(user);
        // Act
        Mockito.when(repository.findAll()).thenReturn(bancoDados);

        List<Usuario> users = usuarioService.findAll();
        // Assert
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("alan", users.get(0).getNome());
    }

    @Test
    public void deveCriarUsuarioNoBanco() {
        Usuario user = new Usuario();
        user.setNome("alan");
        user.setConta("1234");
        user.setIdade(22);
        user.setCpf("123456789");

        Mockito.when(repository.save(user)).thenReturn(salvar(user));

        Usuario usuarioBanco = usuarioService.save(user);
        Assertions.assertEquals(1, usuarioBanco.getId());
    }

    private Usuario salvar (Usuario usuario) {
        usuario.setId(1);
        return usuario;
    }
}
