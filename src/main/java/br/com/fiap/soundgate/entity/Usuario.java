package br.com.fiap.soundgate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SG_AM_USUARIO")
@SequenceGenerator(name="usuario",sequenceName="SG_SQ_USUARIO",allocationSize=1)
public class Usuario {
    @Id
    @GeneratedValue(generator="usuario",strategy=GenerationType.SEQUENCE)
    @Column(name="cd_usuario")
    private int cd;
    @Column(name="desc_login",unique=true,length=30,nullable=false)
    private String login;
    @Column(name="desc_senha",length=20,nullable=false)
    private String senha;
    @Column(name="nm_usuario",length=70,nullable=false)
    private String nome;
    @Column(name="nr_cpf",precision=11,nullable=false)
    private long cpf;
    @Column(name="nr_rg",length=9,nullable=false)
    private String rg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="dt_nascimento",nullable=false)
    private Calendar nascimento;
    @Column(name="nr_telefone",precision=10,nullable=true)
    private int telefone;
    @Column(name="vl_saldo",precision=5,scale=2,nullable=false)
    private double saldo;
    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="cd_endereco",nullable=false)
    private Endereco endereco;
    @JsonIgnore
    @OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Historico> historicos =new ArrayList<Historico>();
    @JsonIgnore
    @OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Ingresso> ingressos = new ArrayList<Ingresso>();

    public Usuario(int cd, String login, String senha, String nome, long cpf, String rg, Calendar nascimento,
                   int telefone, double saldo, Endereco endereco, List<Historico> historicos, List<Ingresso> ingressos) {
        super();
        this.cd = cd;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.saldo = saldo;
        this.endereco = endereco;
        this.historicos = historicos;
        this.ingressos = ingressos;
    }
    public Usuario(int cd, String login, String senha, String nome, long cpf, String rg, Calendar nascimento,
                   int telefone, double saldo, Endereco endereco, List<Historico> historicos) {
        super();
        this.cd = cd;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.saldo = saldo;
        this.endereco = endereco;
        this.historicos = historicos;
    }
    public Usuario(int cd, String nome, long cpf, String rg, Calendar nascimento, int telefone, double saldo,
                   Endereco endereco, List<Historico> historicos) {
        super();
        this.cd = cd;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.saldo = saldo;
        this.endereco = endereco;
        this.historicos = historicos;
    }
    public Usuario() {
        super();
    }
    public Usuario(int cd, String nome, long cpf, String rg, Calendar nascimento, int telefone, double saldo,
                   Endereco endereco) {
        super();
        this.cd = cd;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.saldo = saldo;
        this.endereco = endereco;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public Calendar getNascimento() {
        return nascimento;
    }
    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public void setHistoricos(List<Historico> historicos) {
        this.historicos = historicos;
    }
    public List<Historico> getHistoricos(){
        return this.historicos;
    }
    public void addHistorico(Historico h) {
        h.setUsuario(this);
        this.historicos.add(h);
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public List<Ingresso> getIngressos() {
        return ingressos;
    }
    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
    public void addIngresso(Ingresso ingresso) {
        ingresso.setUsuario(this);
        ingressos.add(ingresso);
    }

}