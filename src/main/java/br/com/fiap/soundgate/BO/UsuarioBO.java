package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Evento;
import br.com.fiap.soundgate.entity.Historico;
import br.com.fiap.soundgate.entity.Ingresso;
import br.com.fiap.soundgate.entity.Usuario;
import br.com.fiap.soundgate.excecao.CadastroException;
import br.com.fiap.soundgate.excecao.SaldoInsuficienteException;
import org.springframework.transaction.annotation.Transactional;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class UsuarioBO {
    private UsuarioRepository usuarioRepository;
    public  void registrarUsuario(Usuario usuario) throws CadastroException {
        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
            throw  new CadastroException();
        usuarioRepository.save(usuario);
    }
    public  Usuario logar(String login,String senha,UsuarioRepository repository){
        return repository.findByLoginAndSenha(login,senha);
    }
    @Transactional
    public Boolean passarCatraca(int usuarioId) throws CadastroException {
        Optional<Usuario> usuario = buscarUsuario(usuarioId);
        if(!usuario.isPresent())
            return false;
        if(usuario.get().getSaldo()-4.20<0)
            return false;
        Historico historico= new Historico();
        historico.setUsuario(usuario.get());
        historico.setData(new GregorianCalendar());
        historico.setDescricao("Passagem de catraca");
        historico.setValor(-4.20);
        HistoricoBO historicoBO = new HistoricoBO();
        historicoBO.registrarHistorico(historico);
        usuario.get().setSaldo(usuario.get().getSaldo()-4.20);
        usuarioRepository.save(usuario.get());
        return true;
    }
    public void comprarIngresso(Usuario usuario, Evento evento, Calendar dataIngresso) throws SaldoInsuficienteException {
        //Checagem se o usuario possui saldo
        if(usuario.getSaldo()-evento.getPreco()<0)
            throw new SaldoInsuficienteException();
        //Atualizalção do saldo do usuario
        usuario.setSaldo(usuario.getSaldo()-evento.getPreco());
        usuarioRepository.save(usuario);
        //Criação do ingresso
        Ingresso ingresso = new Ingresso();
        ingresso.setEvento(evento);
        ingresso.setUsuario(usuario);
        ingresso.setValido(true);
        ingresso.setData(dataIngresso);
        //Registro do ingresso
        IngressoBO ingressoBO = new IngressoBO();
        ingressoBO.registrarIngresso(ingresso);
        //Criação do historico
        Historico historico = new Historico();
        historico.setData(new GregorianCalendar());
        historico.setUsuario(usuario);
        historico.setValor(-evento.getPreco());
        historico.setDescricao("Compra de ingresso para o evento "+evento.getNome());
        //registro do historico
        HistoricoBO historicoBO = new HistoricoBO();
        historicoBO.registrarHistorico(historico);
    }
    public Optional<Usuario> buscarUsuario(int usuarioId){
        return usuarioRepository.findById(usuarioId);
    }
    public Usuario buscarUsuario(String login){
        return usuarioRepository.findByLogin(login);
    }
}
