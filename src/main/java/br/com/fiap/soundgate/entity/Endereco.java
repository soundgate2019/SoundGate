package br.com.fiap.soundgate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SG_AM_ENDERECO")
@SequenceGenerator(name="endereco",sequenceName="SG_SQ_ENDERECO",allocationSize=1)
public class Endereco {
    @Id
    @Column(name="cd_endereco",nullable=false)
    @GeneratedValue(generator="endereco",strategy=GenerationType.SEQUENCE)
    private int cd;
    @Column(name="ds_cep",length=8,nullable=false)
    private String cep;
    @Column(name="ds_logradouro",length=128,nullable=false)
    private String logradouro;
    @Column(name="ds_endereco",length=256)
    private String descricao;

    public Endereco() {
        super();
    }
    public Endereco(int cd, String cep, String logradouro, String descricao) {
        super();
        this.cd = cd;
        this.cep = cep;
        this.logradouro = logradouro;
        this.descricao = descricao;
    }
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}