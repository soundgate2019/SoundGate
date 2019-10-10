package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.*;
import br.com.fiap.soundgate.entity.*;
import br.com.fiap.soundgate.excecao.CadastroException;
import br.com.fiap.soundgate.excecao.EsgotadoException;
import br.com.fiap.soundgate.excecao.SaldoInsuficienteException;
import br.com.fiap.soundgate.webservice.facts.ExtornoFacts;
import br.com.fiap.soundgate.webservice.facts.IngressoFacts;
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
    @Autowired
    private IngressoRepository ingressoRepository;
    @Autowired
    private EventoRepository eventoRepository;
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
    @PutMapping("atualizar")
    public void atualizar(@RequestBody Usuario usuario){
        repository.save(usuario);
    }
    @PostMapping("ingressos")
    public List<Ingresso> ingressos(@RequestBody Usuario usuario){
        return repository.findById(usuario.getCd()).get().getIngressos();
    }
    @PostMapping("historicos")
    public List<Historico> historicos(@RequestBody Usuario usuario){
        return repository.findById(usuario.getCd()).get().getHistoricos();
    }
    @PostMapping("cadastrar")
    public  void cadastrarUsuario(@RequestBody Usuario usuario)throws CadastroException{
        if(repository.findByLogin(usuario.getLogin()) != null)
            throw  new CadastroException();
        repository.save(usuario);
    }
    @PostMapping("adicionarIngresso")
    @Transactional
    public void addicionarIngresso(@RequestBody IngressoFacts facts) throws SaldoInsuficienteException, EsgotadoException {
        Usuario usuario=repository.findById(facts.getUsuarioCd()).get();
        Evento evento = eventoRepository.findById(facts.getEventoCd()).get();
        //Criando Ingresso
        Ingresso ingresso = new Ingresso();
        ingresso.setValido(true);
        ingresso.setData(facts.getData());
        ingresso.setUsuario(usuario);
        ingresso.setEvento(evento);

        //Conferindo se existem ingressos disponiveis para esse evento
        if(!ingressoRepository.findAllByEvento(evento).isEmpty())
            //if(evento.getLugaresPorDia()-ingressoRepository.contarIngressos(evento.getCd(),ingresso.getData())<1)
                throw new EsgotadoException();

        //Coferindo e atualizando informações do usuario
        if(usuario.getSaldo()-evento.getPreco() < 0)
            throw new SaldoInsuficienteException();

        //atualizando usuario
        usuario.setSaldo(usuario.getSaldo() - evento.getPreco());
        //Criando historico
        Historico historico = new Historico();
        historico.setUsuario(usuario);
        historico.setValor(-evento.getPreco());
        historico.setData(new GregorianCalendar());
        historico.setDescricao("Compra de ingresso para o evento "+evento.getNome());

        //colacando os dados no banco
        repository.save(usuario);
        historicoRepository.save(historico);
        ingressoRepository.save(ingresso);

    }
    @PostMapping("extornarIngresso")
    @Transactional
    public void extornarIngresso(@RequestBody ExtornoFacts extornoFacts){
       Usuario usuario = repository.findByLoginAndSenha(extornoFacts.getUsuarioLogin(), extornoFacts.getUsuarioSenha());
       Evento evento = eventoRepository.findById(extornoFacts.getEventoCd()).get();
       Ingresso ingresso = ingressoRepository.findByCdAndEventoAndUsuario(extornoFacts.getIngressoCd(),evento,usuario);

       usuario.setSaldo(usuario.getSaldo()+evento.getPreco());
       Historico historico = new Historico();
       historico.setUsuario(usuario);
       historico.setValor(evento.getPreco());
       historico.setData(new GregorianCalendar());
       historico.setDescricao("Extorno do ingresso para o evento "+evento.getNome());
       historicoRepository.save(historico);
       repository.save(usuario);
       ingressoRepository.delete(ingresso);
    }
}
