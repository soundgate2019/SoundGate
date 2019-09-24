package br.com.fiap.soundgate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SG_AM_HISTORICO")
@IdClass(HistoricoPK.class)
public class Historico {
    @Id
    @SequenceGenerator(name="historico",sequenceName="SG_SQ_HISTORICO",allocationSize=1)
    @GeneratedValue(generator="historico",strategy=GenerationType.SEQUENCE)
    @Column(name="cd_historico")
    private int cd;
    @Id
    @JsonIgnoreProperties("historicos")
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="cd_usuario")
    private Usuario usuario;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="dt_transacao",nullable=false)
    private Calendar data;
    @Column(name="desc_transacao",length=80,nullable=false)
    private String descricao;
    @Column(name="vl_alterado",precision=4,scale=2,nullable=false)
    private double valor;
    public Historico() {
        super();
    }

    public Historico(int cd, Usuario usuario, Calendar data, String descricao, double valor) {
        super();
        this.cd = cd;
        this.usuario = usuario;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Historico(int cd, Usuario usuario, Calendar data, String descricao) {
        super();
        this.cd = cd;
        this.usuario = usuario;
        this.data = data;
        this.descricao = descricao;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Calendar getData() {
        return data;
    }
    public void setData(Calendar data) {
        this.data = data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

}