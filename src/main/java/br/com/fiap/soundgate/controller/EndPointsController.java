package br.com.fiap.soundgate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EndPointsController {
    @GetMapping("/usuarios")
    public String usuarios(){
        return "usuario/home";
    }
    @GetMapping("/usuarios/{id}")
    public String usuariosPorId(){
        return "usuario/home";
    }
    @GetMapping("/usuarios/{id}/endereco")
    public String usuariosPorIdEndereco(){
        return "usuario/home";
    }
    @GetMapping("/usuarios/{id}/ingressos")
    public String usuariosPorIdIngressos(){
        return "usuario/home";
    }
    @GetMapping("/empresas")
    public String empresas(){
        return "usuario/home";
    }

    @GetMapping("/empresas/{id}")
    public String empresasPorId(){
        return "usuario/home";
    }

    @GetMapping("/empresas/{id}/endereco")
    public String empresasPorIdEndereco(){
        return "usuario/home";
    }
    @GetMapping("/empresas/{id}/eventos")
    public String empresasPorIdEventos(){
        return "usuario/home";
    }

}
