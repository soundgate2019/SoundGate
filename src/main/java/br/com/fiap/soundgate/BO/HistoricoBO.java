package br.com.fiap.soundgate.BO;

import br.com.fiap.soundgate.DAO.HistoricoRepository;
import br.com.fiap.soundgate.entity.Historico;

public class HistoricoBO {
    private HistoricoRepository historicoRepository;
    public void registrarHistorico(Historico historico){
            historicoRepository.save(historico);
    }
}
