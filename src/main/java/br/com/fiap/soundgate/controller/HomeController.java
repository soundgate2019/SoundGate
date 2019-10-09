package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index(){
    return "usuario/home";
    }
    @GetMapping("/onde")
    public String ondeUsar(){
        return "usuario/onde";
    }
    @GetMapping("/como")
    public String comoUsar(){ return "usuario/como"; }


}
