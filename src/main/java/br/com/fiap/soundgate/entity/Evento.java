package br.com.fiap.soundgate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="SG_AM_EVENTO")
@SequenceGenerator(name="evento",sequenceName="SG_SQ_EVENTO",allocationSize=1)
public class Evento {
    @Id
    @GeneratedValue(generator="evento",strategy=GenerationType.SEQUENCE)
    @Column(name="cd_evento")
    private int cd;
    @Column(name="nm_evento",length=70,nullable=false)
    private String nome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="dt_pri_dia",nullable=false)
    private Calendar primeiroDia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="dt_ult_dia",nullable=false)
    private Calendar ultimoDia;
    @JsonIgnoreProperties("eventos")
    @ManyToOne
    @JoinColumn(name="cd_empresa")
    private Empresa empresa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cd_endereco",nullable=false)
    private Endereco endereco;
    @Column(name="vl_evento",precision=4,scale=2,nullable=false)
    private double preco;
    @Column(name="num_lugares",precision=5,nullable=false)
    private int lugaresPorDia;
    @JsonIgnore
    @OneToMany(mappedBy="evento",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Ingresso> ingressos = new ArrayList<Ingresso>();
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name="hr_ini_evento",nullable=false)
    private Date horarioInicial;
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name="hr_li_evento",nullable=false)
    private Date horarioLimite;
    @Column(name="ft_evento",nullable = true)
    private String fotoEvento;

    public Evento(int cd, String nome, Calendar primeiroDia, Calendar ultimoDia, Empresa empresa, Endereco endereco,
                  double preco, int lugaresPorDia, List<Ingresso> ingressos, Date horarioInicial, Date horarioLimite, String fotoEvento) {
        super();
        this.cd = cd;
        this.nome = nome;
        this.primeiroDia = primeiroDia;
        this.ultimoDia = ultimoDia;
        this.empresa = empresa;
        this.endereco = endereco;
        this.preco = preco;
        this.lugaresPorDia = lugaresPorDia;
        this.ingressos = ingressos;
        this.horarioInicial = horarioInicial;
        this.horarioLimite = horarioLimite;
        this.fotoEvento = fotoEvento;
    }
    public Evento(int cd, String nome, Calendar primeiroDia, Calendar ultimoDia, Empresa empresa, Endereco endereco,
                  double preco, int lugaresPorDia, List<Ingresso> ingressos) {
        super();
        this.cd = cd;
        this.nome = nome;
        this.primeiroDia = primeiroDia;
        this.ultimoDia = ultimoDia;
        this.empresa = empresa;
        this.endereco = endereco;
        this.preco = preco;
        this.lugaresPorDia = lugaresPorDia;
        this.ingressos = ingressos;
    }
    public Evento(int cd, String nome, Calendar primeiroDia, Calendar ultimoDia, Empresa empresa,
                  Endereco endereco) {
        super();
        this.cd = cd;
        this.nome = nome;
        this.primeiroDia = primeiroDia;
        this.ultimoDia = ultimoDia;
        this.empresa = empresa;
        this.endereco = endereco;
    }
    public Evento() {
        super();
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
    public Calendar getPrimeiroDia() {
        return primeiroDia;
    }
    public void setPrimeiroDia(Calendar primeiroDia) {
        this.primeiroDia = primeiroDia;
    }
    public Calendar getUltimoDia() {
        return ultimoDia;
    }
    public void setUltimoDia(Calendar ultimoDia) {
        this.ultimoDia = ultimoDia;
    }
    public Empresa getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public List<Ingresso> getIngressos() {
        return ingressos;
    }
    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
    public void addIngresso(Ingresso i) {
        i.setEvento(this);
        this.ingressos.add(i);
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getLugaresPorDia() {
        return lugaresPorDia;
    }
    public void setLugaresPorDia(int lugaresPorDia) {
        this.lugaresPorDia = lugaresPorDia;
    }
    public Date getHorarioInicial() {
        return horarioInicial;
    }
    public void setHorarioInicial(Date horarioInicial) {
        this.horarioInicial = horarioInicial;
    }
    public Date getHorarioLimite() {
        return horarioLimite;
    }
    public void setHorarioLimite(Date horarioLimite) {
        this.horarioLimite = horarioLimite;
    }

    public String getFotoEvento() {
        return fotoEvento;
    }

    public void setFotoEvento(String fotoEvento) {
        this.fotoEvento = fotoEvento;
    }
}