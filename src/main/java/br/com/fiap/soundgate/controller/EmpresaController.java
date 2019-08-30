package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.EmpresaRepository;
import br.com.fiap.soundgate.entity.Empresa;
import br.com.fiap.soundgate.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpresaController {
    @Autowired
    private EmpresaRepository empresaRepository;
    @GetMapping("/empresa/cadastro")
    public String cadastrarEmpresa(){
        return "empresa/cadastroEmpresa";
    }
    @PostMapping("/empresa/cadastro")
    public String cadastrarEmpresa(Empresa empresa,String logradouro,String cep,String descricao){
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setDescricao(descricao);
        endereco.setLogradouro(logradouro);
        empresa.setEndereco(endereco);
        empresaRepository.save(empresa);
        return "empresa/cadastroEmpresa";
    }
}
