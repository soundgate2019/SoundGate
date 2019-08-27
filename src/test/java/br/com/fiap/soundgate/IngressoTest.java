package br.com.fiap.soundgate;

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
    private IngressoRepository repository;
    @Autowired
    private UsuarioRepository repositoryU;
    @Test
    public void contextLoads() {
        Date date = new Date();
        Ingresso ingresso = new Ingresso();
        ingresso.setData(new GregorianCalendar());
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
        Empresa empresa = new Empresa();
        empresa.setNome("Empresa 1");
        empresa.setCnpj(1234567891);
        Endereco enderecoEmpresa = new Endereco();
        enderecoEmpresa.setLogradouro("Rua empresa");
        enderecoEmpresa.setDescricao("Endereco empresa");
        enderecoEmpresa.setCep("05060051");
        empresa.setEndereco(enderecoEmpresa);
        evento.setEmpresa(empresa);
        ingresso.setEvento(evento);
        ingresso.setUsuario(repositoryU.findById(2).get());
        ingresso.setValido(true);
        repository.save(ingresso);
    }
}
