package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.IngressoRepository;
import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Ingresso;
import br.com.fiap.soundgate.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("SoundGateWB")
public class SoundGateWB {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private IngressoRepository repositoryI;
    @GetMapping("Usuario")
    public Usuario oi(){
        return repository.findById(2).get();
    }
    @GetMapping("{cd}")
    public boolean usuarioExiste(@PathVariable int cd){
        Optional<Usuario> usuario = repository.findById(cd);
        return usuario.isPresent();
    }
    @GetMapping("Ingresso")
    public Ingresso seila(){
        return repository.findById(2).get().getIngressos().get(0);
    }
}
