package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
