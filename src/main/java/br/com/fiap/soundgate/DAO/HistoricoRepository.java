package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Historico;
import br.com.fiap.soundgate.entity.HistoricoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, HistoricoPK> {
}
