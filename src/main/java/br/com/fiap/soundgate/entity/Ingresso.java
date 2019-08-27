package br.com.fiap.soundgate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="SG_AM_INGRESSO")
@IdClass(IngressoPK.class)
public class Ingresso {
    @Id
    @SequenceGenerator(name="ingresso",sequenceName="SG_SQ_INGRESSO",allocationSize=1)
    @GeneratedValue(generator="ingresso",strategy=GenerationType.SEQUENCE)
    @Column(name="cd_ingresso")
    private int cd;
    @Id
    @JsonIgnoreProperties("ingressos")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cd_evento")
    private Evento evento;
    @Temporal(TemporalType.DATE)
    @Column(name="dt_evento")
    private Calendar data;
    @Column(name="estaddo_ing",nullable=false)
    private boolean valido;
    @JsonIgnoreProperties({"ingressos","historicos"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cd_usuario")
    private Usuario usuario;


    public Ingresso(int cd, Evento evento, Calendar data, boolean valido, Usuario usuario) {
        super();
        this.cd = cd;
        this.evento = evento;
        this.data = data;
        this.valido = valido;
        this.usuario = usuario;
    }
    public Ingresso() {
        super();
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public Calendar getData() {
        return data;
    }
    public void setData(Calendar data) {
        this.data = data;
    }
    public boolean isValido() {
        return valido;
    }
    public void setValido(boolean valido) {
        this.valido = valido;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}