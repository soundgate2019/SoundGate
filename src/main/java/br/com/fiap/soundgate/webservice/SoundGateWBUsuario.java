package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.*;
import br.com.fiap.soundgate.entity.*;
import br.com.fiap.soundgate.webservice.facts.UsuarioFacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("SoundGateWB/Usuario")
public class SoundGateWBUsuario {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @PostMapping("logar")
    public Usuario logar(@RequestBody UsuarioFacts usuarioFacts){
        return repository.findByLoginAndSenha(usuarioFacts.getLogin(), usuarioFacts.getSenha());
    }
    @PutMapping("adicionarSaldo")
    @Transactional
    public void adicionarSaldo(@RequestBody Usuario usuario){
        //Pegando as informações desse usuario para termos o saldo antigo dele
        Usuario usuarioantigo = repository.findById(usuario.getCd()).get();
        double valor =usuario.getSaldo() - usuarioantigo.getSaldo();
        //criando historico
        Historico historico = new Historico();
        historico.setDescricao("Compra de saldo");
        historico.setData(new GregorianCalendar());
        historico.setValor(valor);
        historico.setUsuario(usuario);

        //salvando dados
        repository.save(usuario);
        historicoRepository.save(historico);
    }
    @PostMapping("ingressos")
    public List<Ingresso> ingressos(@RequestBody Usuario usuario){
        return repository.findById(usuario.getCd()).get().getIngressos();
    }
    @PostMapping("Historicos")
    public List<Historico> historicos(@RequestBody Usuario usuario){
        return repository.findById(usuario.getCd()).get().getHistoricos();
    }
}
