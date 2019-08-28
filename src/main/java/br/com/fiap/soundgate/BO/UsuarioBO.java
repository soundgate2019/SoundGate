package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Historico;
import br.com.fiap.soundgate.entity.Usuario;
import br.com.fiap.soundgate.excecao.CadastroException;

import javax.transaction.Transactional;
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
        if(usuario == null)
            throw new CadastroException();
        Historico historico= new Historico();
        historico.setUsuario(usuario.get());
        historico.setData(new GregorianCalendar());
        historico.setDescricao("Passagem de catraca");
        historico.setValor(4.20);
        HistoricoBO historicoBO = new HistoricoBO();
        historicoBO.registrarHistorico(historico);
        return true;
    }
    public void comprarIngresso(){

    }
    public Optional<Usuario> buscarUsuario(int usuarioId){
        return usuarioRepository.findById(usuarioId);
    }
    public Usuario buscarUsuario(String login){
        return usuarioRepository.findByLogin(login);
    }
}
