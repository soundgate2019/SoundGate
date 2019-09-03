package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Endereco;
import br.com.fiap.soundgate.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/cadastro")
    public String cadastrarUsuario(){
        return "usuario/cadastro";
    }

    @PostMapping("/usuario/cadastro")
    public String cadastrarUsuario(Usuario usuario,String logradouro,String cep,String descricao){
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setDescricao(descricao);
        endereco.setLogradouro(logradouro);
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
        return "perfil";
    }
}
