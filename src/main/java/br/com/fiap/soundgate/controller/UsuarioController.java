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
import java.util.ArrayList;
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
                return "usuario/cadastro";
            }
            /*List<String> erros = new ArrayList<String>();
            System.out.println(usuario.getLogin());
            if(usuario.getLogin()=="")
                erros.add("email");
            if(usuario.getCpf()<0)
                erros.add("cpf");
            if(usuario.getNome()=="")
                erros.add("nome");
            if(usuario.getRg()=="")
                erros.add("rg");
            if(usuario.getSenha()=="")
                erros.add("senha");
            if(usuario.getTelefone()<0)
                erros.add("numero de celular");
            if(logradouro==null)
                erros.add("logradouro");
            if(descricao==null)
                erros.add("descrição");
            if(cep==null)
                erros.add("cep");
            if(erros.size()>0){
                String mensagem = "";
                for(int i=0; i<erros.size(); i++){
                    if(i==erros.size()){
                        mensagem+=erros.get(i);
                    }else {
                        mensagem+=erros.get(i) + ", ";
                    }
                }
                mensagem="Os campos: "+mensagem+". São obrigatórios";
                redirectAttributes.addFlashAttribute("msg", mensagem);
                return "usuario/cadastro";
            }*/
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

    @GetMapping("/usuario/compra")
    public String comprar(){
        return "usuario/compra";
    }

    @GetMapping("/usuario/adicionar")
    public String adicionar(){return "usuario/login";}

    @PostMapping("/usuario/adicionar")
    public String adicionarCred(@RequestParam(value = "valor") double valor, Model model, HttpSession saldoSe,
                                @RequestParam(value = "codigo") int codigo){
        if (valor <= 0){
            model.addAttribute("msg", "O valor do saldo deve ser maior que 0");
            return "usuario/compra";
        }
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
}
