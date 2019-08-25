package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Integer> {
}
