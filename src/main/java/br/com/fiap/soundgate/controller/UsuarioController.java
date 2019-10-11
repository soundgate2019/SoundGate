package br.com.fiap.soundgate.controller;

import br.com.fiap.soundgate.DAO.HistoricoRepository;
import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.Endereco;
import br.com.fiap.soundgate.entity.Historico;
import br.com.fiap.soundgate.entity.Usuario;
import br.com.fiap.soundgate.excecao.CadastroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/usuario/cadastro")
    public String cadastrarUsuario(){
        return "usuario/cadastro";
    }

    @PostMapping("/usuario/cadastro")
        public String cadastrarUsuario(RedirectAttributes redirectAttributes, HttpSession session,
                                       Usuario usuario,String logradouro,String cep,String descricao) throws CadastroException {
            if(usuarioRepository.findByLogin(usuario.getLogin()) != null) {
                redirectAttributes.addFlashAttribute("msg", "O email já foi cadastrado");
                return "redirect:/usuario/cadastro";
            }
            session.setAttribute("usuario", usuario);
            Endereco endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setDescricao(descricao);
            endereco.setLogradouro(logradouro);
            usuario.setEndereco(endereco);
            usuarioRepository.save(usuario);
            return "redirect:/usuario/perfil";
        }

    @GetMapping("/usuario/login")
    public String loginUsuario(){
        return "usuario/login";
    }
    @PostMapping("/usuario/login")
    public String loginUsuario(HttpSession session, String login, String senha, RedirectAttributes redirectAttributes){
        Usuario u = usuarioRepository.findByLoginAndSenha(login, senha);
        if (u!=null){
            session.setAttribute("usuario", u);
            return "usuario/compra";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Login ou senha incorretos");
            return "redirect:/usuario/login";
        }
    }

    @GetMapping("/usuario/perfil")
    public String perfilUsuario(){
        return "usuario/perfil";
    }

    @GetMapping("/usuario/compra")
    public String comprar(){
        return "usuario/compra";
    }

    @GetMapping("/usuario/adicionar")
    public String adicionar(){return "usuario/login";}

    @PostMapping("/usuario/adicionar")
    public String adicionarCred(@RequestParam(value = "valor") double valor, HttpSession saldoSe,
                                @RequestParam(value = "codigo") int codigo){
        Usuario usuario = usuarioRepository.findByCd(codigo);
        double saldoAtual = usuario.getSaldo();
        usuario.setSaldo(saldoAtual+valor);
        Historico h = new Historico(usuario, Calendar.getInstance(new Locale("pt", "BR")),
                "Compra de saldo", valor);
        saldoSe.setAttribute("usuario", usuario);

        historicoRepository.save(h);
        usuarioRepository.save(usuario);
        return "redirect:/usuario/compra";
    }
    @GetMapping("/usuario/deslogar")
    public String deslogar(HttpSession session){
        session.removeAttribute("usuario");
        return "usuario/login";
    }

    @GetMapping("/usuario/historico")
    public String historico(HttpSession session){
        Usuario u = (Usuario) session.getAttribute("usuario");
        List<Historico> historicos = usuarioRepository.findById(u.getCd()).get().getHistoricos();

        session.setAttribute("historico", historicos);
        return "usuario/historico";
    }
    @GetMapping("/usuario/atualizar")
    public String redir(HttpSession session){
        Usuario u = (Usuario) session.getAttribute("usuario");
        Usuario uSes = usuarioRepository.findByCd(u.getCd());
        session.setAttribute("usuario", uSes);
        return "usuario/compra";
    }
}
