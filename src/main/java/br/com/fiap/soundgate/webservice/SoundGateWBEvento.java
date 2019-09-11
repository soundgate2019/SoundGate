package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.EventoRepository;
import br.com.fiap.soundgate.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("SoundGateWB/Evento")
public class SoundGateWBEvento {
    @Autowired
    EventoRepository repository;

    @GetMapping("eventos")
    public List<Evento> eventos(){
        return repository.findAll();
    }
    @GetMapping({"cd"})
    public Evento evento(@PathVariable int cd){
        return repository.findById(cd).get();
    }
}
