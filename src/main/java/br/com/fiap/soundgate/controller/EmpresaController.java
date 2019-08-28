package br.com.fiap.soundgate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaController {
    @GetMapping("/empresa/cadastro")
    public String cadastrarEmpresa(){
        return "empresa/cadastroEmpresa";
    }
}
