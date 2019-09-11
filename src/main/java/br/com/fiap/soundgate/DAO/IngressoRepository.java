package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Evento;
import br.com.fiap.soundgate.entity.Ingresso;
import br.com.fiap.soundgate.entity.IngressoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface IngressoRepository extends JpaRepository<Ingresso, IngressoPK> {
    @Query("from Ingresso i where i.usuario.cd = ?1 and i.evento.cd = ?2 and i.data = ?3")
    Ingresso buscarIngressoAPI(int usuarioCd, int eventoCd, Calendar data);
    @Query("select count (i) from Ingresso i  where i.evento.cd = ?1 and i.data = ?2")
    int contarIngressos(int eventoCd, Calendar data);
    List<Ingresso> findAllByEvento(Evento evento);
}
