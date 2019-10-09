package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.EmpresaRepository;
import br.com.fiap.soundgate.DAO.EventoRepository;
import br.com.fiap.soundgate.entity.Empresa;
import br.com.fiap.soundgate.entity.Endereco;
import br.com.fiap.soundgate.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;
    private EmpresaRepository empresaRepository;

    @GetMapping("/evento/cadastro")
    public String cadastrarEvento(){
        return "evento/cadastroEvento";
    }
    @PostMapping("/evento/cadastro")
    public String cadastrarEvento(Evento evento, String logradouro,String cep,String descricao){
        Endereco e = new Endereco();
        e.setCep(cep);
        e.setDescricao(descricao);
        e.setLogradouro(logradouro);
        evento.setEndereco(e);
        evento.setEmpresa(empresaRepository.findByNome("sehloiro"));
        eventoRepository.save(evento);
        return "evento/cadastroEvento";
    }
}
