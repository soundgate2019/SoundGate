package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
     Optional<Usuario> findByLoginAndSenha(String login,String senha);
}
