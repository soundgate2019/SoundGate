package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Ingresso;
import br.com.fiap.soundgate.entity.IngressoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Calendar;

public interface IngressoRepository extends JpaRepository<Ingresso, IngressoPK> {
    @Query("from Ingresso i where i.usuario.cd = ?1 and i.evento.cd = ?2 and i.data = ?3")
    Ingresso buscarIngressoAPI(int usuarioCd, int eventoCd, Calendar data);
}
