package br.com.fiap.soundgate;

import br.com.fiap.soundgate.DAO.EmpresaRepository;
import br.com.fiap.soundgate.DAO.EventoRepository;
import br.com.fiap.soundgate.DAO.IngressoRepository;
import br.com.fiap.soundgate.DAO.UsuarioRepository;
import br.com.fiap.soundgate.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IngressoTest {
    @Autowired
    private EmpresaRepository repository;
    @Autowired
    private EventoRepository repositoryU;
    @Test
    public void contextLoads() {
        Date date = new Date();
        Evento evento = new Evento();
        evento.setHorarioInicial(date);
        evento.setHorarioLimite(date);
        evento.setLugaresPorDia(50);
        evento.setPrimeiroDia(new GregorianCalendar());
        evento.setUltimoDia(new GregorianCalendar());
        evento.setNome("Teste de evento");
        evento.setPreco(50.50);
        Endereco enderecoEvento = new Endereco();
        enderecoEvento.setCep("05060050");
        enderecoEvento.setDescricao("Endereco evento");
        enderecoEvento.setLogradouro("Rua evento");
        evento.setEndereco(enderecoEvento);
        evento.setEmpresa(repository.findById(1).get());
        repositoryU.save(evento);
    }
}
