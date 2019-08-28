package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.EmpresaRepository;
import br.com.fiap.soundgate.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpresaBO {
    @Autowired
    private EmpresaRepository empresaRepository;
    public void registrarEmpresa(Empresa empresa){
        empresaRepository.save(empresa);
    }
}
