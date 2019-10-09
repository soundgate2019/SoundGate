package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.EventoRepository;
import br.com.fiap.soundgate.DAO.IngressoRepository;
import br.com.fiap.soundgate.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("SoundGateWB/Evento")
public class SoundGateWBEvento {
    @Autowired
    EventoRepository repository;
    @Autowired
    IngressoRepository ingressoRepository;
    @GetMapping("eventos")
    public List<Evento> eventos(){
        return repository.findAll();
    }
    @GetMapping({"cd"})
    public Evento evento(@PathVariable int cd){
        return repository.findById(cd).get();
    }
    @GetMapping("disponiveis")
    public int ingressosRestantes( @RequestParam int eventoId, @RequestParam Calendar data){
        Evento evento=repository.findById(eventoId).get();
        if(ingressoRepository.findAllByEvento(evento).isEmpty())
            return evento.getLugaresPorDia();
        return evento.getLugaresPorDia()-ingressoRepository.contarIngressos(evento.getCd(),data);
    }

    @DeleteMapping("deletar")
    public void deletar(@RequestBody Evento evento){
        repository.delete(evento);
    }
}
