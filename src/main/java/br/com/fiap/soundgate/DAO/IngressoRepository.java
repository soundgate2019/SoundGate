package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Ingresso;
import br.com.fiap.soundgate.entity.IngressoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressoRepository extends JpaRepository<Ingresso, IngressoPK> {
}
