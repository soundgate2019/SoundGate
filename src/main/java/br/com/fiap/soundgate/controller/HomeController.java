package br.com.fiap.soundgate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/Home")
    public String index(){
        return"home.xhtml";
    }

}
