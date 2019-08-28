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

        Usuario usuario = new Usuario();
        usuario.setCpf(42742039805l);
        usuario.setLogin("zaiamlata2");
        usuario.setSenha("1234");
        usuario.setRg("507834549");
        usuario.setNome("Victor Ribeiro");
        usuario.setTelefone(38646969);
        usuario.setSaldo(50);
        usuario.setNascimento(new GregorianCalendar());
        Endereco endereco = new Endereco();
        endereco.setCep("05060054");
        endereco.setDescricao("Endereco Usuario");
        endereco.setLogradouro("Rua Usuario");
        usuario.setEndereco(endereco);
        repository.save(usuario);
    }
    @Test
    public void pesquisaNome(){
        Usuario usuario = repository.findByLogin("zaiamlata4");
        System.out.println(usuario);
    }
    @Test
    public void pesquisaNomeSenha(){
        Usuario usuario = repository.findByLoginAndSenha("zaiamlata4","1234");
        System.out.println(usuario);
    }
}

