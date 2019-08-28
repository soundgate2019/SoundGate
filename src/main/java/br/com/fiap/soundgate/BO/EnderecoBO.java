package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.EnderecoRepository;
import br.com.fiap.soundgate.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoBO {
    @Autowired
    private EnderecoRepository enderecoRepository;
    public void registrarEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
    }
}
