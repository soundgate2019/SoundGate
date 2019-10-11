package br.com.fiap.soundgate.DAO;

import br.com.fiap.soundgate.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {
    Empresa findByNome(String nome);
}
