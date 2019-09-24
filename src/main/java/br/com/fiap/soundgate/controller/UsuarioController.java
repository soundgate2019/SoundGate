package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Endereco;
import br.com.fiap.soundgate.entity.Usuario;
import br.com.fiap.soundgate.excecao.CadastroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/cadastro")
    public String cadastrarUsuario(){
        return "usuario/cadastro";
    }

    @PostMapping("/usuario/cadastro")
    public String cadastrarUsuario(Model model, Usuario usuario,String logradouro,String cep,String descricao) throws CadastroException {
        if(usuarioRepository.findByLogin(usuario.getLogin()) != null)
            throw  new CadastroException();
        model.addAttribute("usuario", usuario);
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setDescricao(descricao);
        endereco.setLogradouro(logradouro);
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
        return "usuario/perfil";
    }

    @GetMapping("/usuario/login")
    public String loginUsuario(){
        return "usuario/login";
    }
    @PostMapping("/usuario/login")
    public String loginUsuario(Model model, String login, String senha, RedirectAttributes redirectAttributes){
        Usuario u = usuarioRepository.findByLoginAndSenha(login, senha);
        if (u!=null){
            model.addAttribute("usuario", u);
            return "usuario/perfil";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Login ou senha incorretos");
            return "redirect:/usuario/login";
        }
    }

    @GetMapping("/usuario/perfil")
    public String perfilUsuario(){
        return "usuario/perfil";
    }
}
