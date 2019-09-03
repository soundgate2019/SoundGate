package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
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
        return "cadastro";
    }

    @PostMapping("/usuario/cadastro")
    public String cadastrarUsuario(Usuario usuario){
        usuario.setNascimento(Calendar.getInstance());
        usuarioRepository.save(usuario);
        return "perfil";
    }
}
