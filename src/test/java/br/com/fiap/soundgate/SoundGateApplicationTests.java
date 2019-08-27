package br.com.fiap.soundgate;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Endereco;
import br.com.fiap.soundgate.entity.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.GregorianCalendar;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoundGateApplicationTests {
    @Autowired
    private UsuarioRepository repository;
    @Test
    public void contextLoads() {

        Optional<Usuario> usuario = repository.findByLoginAndSenha("zaiamlata3","1234");
        System.out.println(usuario.get().getCd());
    }
}

