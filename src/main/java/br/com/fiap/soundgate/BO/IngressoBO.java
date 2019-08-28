package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.IngressoRepository;
import br.com.fiap.soundgate.entity.Ingresso;
import org.springframework.beans.factory.annotation.Autowired;

public class IngressoBO {
    @Autowired
    private IngressoRepository repository;
    public void registrarIngresso(Ingresso ingresso){
        repository.save(ingresso);
    }
}
