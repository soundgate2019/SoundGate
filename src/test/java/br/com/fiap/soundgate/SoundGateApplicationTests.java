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

@SpringBootTest
@RunWith(SpringRunner.class)
public class SoundGateApplicationTests {
    @Autowired
    private UsuarioRepository repository;
    @Test
    public void contextLoads() {
        Usuario usuario = new Usuario();
        usuario.setCpf(42742039805l);
        usuario.setLogin("zaiamlata3");
        usuario.setSenha("1234");
        Endereco endereco = new Endereco();
        endereco.setCep("05060050");
        endereco.setDescricao("Ap.16");
        endereco.setLogradouro("R.luís martin");
        usuario.setEndereco(endereco);
        usuario.setNascimento(new GregorianCalendar());
        usuario.setNome("Victor Ribeiro de Lima");
        usuario.setRg("507834549");
        usuario.setSaldo(0);
        usuario.setTelefone(38646969);
        System.out.println("usuario foi");
        repository.save(usuario);
    }
}

