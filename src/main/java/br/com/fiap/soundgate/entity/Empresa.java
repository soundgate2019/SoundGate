package br.com.fiap.soundgate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SG_AM_EMPRESA")
@SequenceGenerator(name="empresa",sequenceName="SG_SQ_EMPRESA",allocationSize=1)
public class Empresa {
    @Id
    @Column(name="cd_empresa")
    @GeneratedValue(generator="empresa",strategy=GenerationType.SEQUENCE)
    private int cd;
    @Column(name="nr_cnpj",precision=14,nullable=false)
    private long cnpj;
    @Column(name="nm_empresa",length=60,nullable=false)
    private String nome;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cd_endereco",nullable=false)
    private Endereco endereco;
    @OneToMany(mappedBy="empresa",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Evento> eventos= new ArrayList<Evento>();

    public Empresa(int cd, long cnpj, String nome, Endereco endereco, List<Evento> eventos) {
        super();
        this.cd = cd;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.eventos = eventos;
    }
    public Empresa() {
        super();
    }
    public Empresa(int cd, long cnpj, String nome, Endereco endereco) {
        super();
        this.cd = cd;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
    }
    public void addEventoBean(Evento e) {
        e.setEmpresa(this);
        this.eventos.add(e);
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public long getCnpj() {
        return cnpj;
    }
    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}