package br.com.fiap.soundgate.webservice;

import br.com.fiap.soundgate.DAO.*;
import br.com.fiap.soundgate.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("SoundGateWB")
public class SoundGateWB {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private IngressoRepository repositoryI;
    @Autowired
    private EnderecoRepository repositoryE;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @GetMapping("usuario")
    public Usuario oi() {
        return repository.findById(2).get();
    }
    @GetMapping("{cd}")
    public boolean usuarioExiste(@PathVariable int cd){
        Optional<Usuario> usuario = repository.findById(cd);
        return usuario.isPresent();
    }
    @PostMapping("usuario/login")
    public boolean logarUsuario(@RequestBody(required = false) String login,@RequestBody(required = false) String senha){
        System.out.println(login);
        if(repository.findByLoginAndSenha(login,senha)==null)
            return false;
        return  true;
    }
    @GetMapping("Ingresso")
    public Ingresso seila(){
        return repository.findById(2).get().getIngressos().get(0);
    }
    @GetMapping("Enderecos")
    public List<Endereco> enderecos(){
        return  repositoryE.findAll();
    }
    @GetMapping("Empresas")
    public List<Empresa> empresas(){
        return  empresaRepository.findAll();
    }
    @GetMapping("Eventos")
    public List<Evento> eventos(){
        return eventoRepository.findAll();
    }
}
